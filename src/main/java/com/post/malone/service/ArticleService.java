package com.post.malone.service;

import com.post.malone.dto.ArticleForm;
import com.post.malone.entity.Article;
import com.post.malone.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service // 서비스 선언! (서비스 객체를 스프링부트에 생성) , 주방장 역할
public class ArticleService {

    @Autowired // DI
    private ArticleRepository articleRepository;  // 주방 보조 역할

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null) { // 기존에 있던 id에 같은 id를 넣고 생성시 내용이 수정되는 것을 해결, POST 요청은 글을 생성
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {

        // 1: 수정용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());
        // 2: 대상 엔티티를 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 3: 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
        if( target == null || id != article.getId()) {
            // 400, 잘못된 응답 요청
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }
        // 4: 업데이트
        target.patch(article); // null 값이 들어가는것을 막기 위해 patch 메서드 실행
        Article updated = articleRepository.save(target); // patch 된 target 을 저장한다.
        return updated;
    }

    public Article delete(Long id) {
        // 1: 대상 엔티티 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 2: 잘못된 요청 처리
        if (target == null) {return null;}

        // 3: 데상 삭제 후 응답 반환
        articleRepository.delete(target);
        return target;
    }

    @Transactional // 해당 메서드를 트랜잭션으로 묶는다.
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // dto 묶음을 entity 묶음으로 변환
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        // entity 묶음을 DB로 저장
        articleList.stream().forEach(article -> articleRepository.save(article));
        // 강제 예회 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결재 실패!")
        );
        // 결과값 반환
        return articleList;
    }
}
