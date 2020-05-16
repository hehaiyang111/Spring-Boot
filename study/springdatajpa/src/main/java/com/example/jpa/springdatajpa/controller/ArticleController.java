    package com.example.jpa.springdatajpa.controller;/**
     * @Description TO
     * @Author hehaiyang
     * @Date 2020/5/12 11:07
     **/

    import com.example.jpa.springdatajpa.entity.Article;
    import com.example.jpa.springdatajpa.service.ArticleService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Sort;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.List;

    /**
     *@Description TO
     *@Author hehaiyang
     *@Date 2020/5/12 11:07
     **/
    @RestController
    @RequestMapping("/article")
    public class ArticleController {

        @Autowired
        ArticleService articleServicel;

        /**
         *排序查询
         * @return 查询返回的数据
         */
        @RequestMapping("/sort")
        public Iterable<Article> sortArticle(){
            Sort id = Sort.by(Sort.Direction.DESC, "id");
            Iterable<Article> all = articleServicel.findAll(id);
            return all;
        }
        /**
         *分页查询
         */
        @RequestMapping("/page/{pageNum}/{size}")
        public Iterable<Article> pageArticle(@PathVariable("pageNum") int page, @PathVariable("size") int size){
            Sort by = Sort.by(Sort.Direction.DESC,"id");
            /**
             * 封装分页实体
             * 参数1：pageIndex表示当前查询的是第几页(默认从0开始，0表示第一页)
             * 参数2：表示每页展示多少数据，现在设置每页展示2条数据
             * 参数3：封装排序对象，根据该对象的参数指定根据id降序查询
             */
            PageRequest page1 = PageRequest.of(page - 1, size, by);
            Page<Article> allByPage = articleServicel.findAllByPage(page1);
            System.out.println(allByPage.getTotalPages()+"总页数");
            System.out.println(allByPage.getTotalElements()+"总记录数");
            System.out.println((allByPage.getNumber()+1)+"当前查询第几页");
            System.out.println(allByPage.getNumberOfElements()+"当前页面的记录数");

            //分页获取所有数据
            List<Article> content = allByPage.getContent();
            return content;

        }
    }
