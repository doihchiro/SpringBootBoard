package com.post.malone.api;

import com.post.malone.dto.ArticleForm;
import com.post.malone.entity.Article;
import com.post.malone.repository.ArticleRepository;
import com.post.malone.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController // RestAPI 용 컨트롤러, 데이터(JSON)를 반환 , 웨이터 역할
public class ArticleApiController {

    @Autowired // DI, 생성 객체를 가져와 연결
    private ArticleService articleService;  // 주방장 역할
//    private ArticleRepository articleRepository;

    // GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    // POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto);
        return (updated != null) ?
               ResponseEntity.status(HttpStatus.OK).body(updated) :
               ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

//        // 1: 수정용 엔티티 생성
//        Article article = dto.toEntity();
//        log.info("id: {}, article: {}", id, article.toString());
//        // 2: 대상 엔티티를 조회
//        Article target = articleRepository.findById(id).orElse(null);
//        // 3: 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
//        if( target == null || id != article.getId()) {
//            // 400, 잘못된 응답 요청
//            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        // 4: 업데이트 및 정상 응답(200)
//        target.patch(article);
//        Article updated = articleRepository.save(target);
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        // 1: 대상 엔티티 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//        // 2: 잘못된 요청 처리(대상이 없는 경우)
//        if(target == null)
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        // 3: 데상 삭제 후 응답 반환
//        articleRepository.delete(target);
    }

    // 트랜잭션 -> 실패 -> 롤백
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
