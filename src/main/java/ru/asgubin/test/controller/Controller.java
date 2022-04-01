package ru.asgubin.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.asgubin.test.entity.Articles;
import ru.asgubin.test.entity.Balance;
import ru.asgubin.test.entity.Operations;
import ru.asgubin.test.service.ArticleService;
import ru.asgubin.test.service.BalanceService;
import ru.asgubin.test.service.OperationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {

    private OperationService operationService;
    private ArticleService articleService;
    private BalanceService balanceService;

    @Autowired
    public void setOperationService(OperationService operationService) {

        this.operationService = operationService;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {

        this.articleService = articleService;
    }

    @Autowired
    public void setBalanceService(BalanceService balanceService) {

        this.balanceService =   balanceService;
    }

    @GetMapping("/operations")
    public String getOperationsPage(Model model) {
        List<Operations> operations = operationService.findAll();
        model.addAttribute("operations", operations);
        return "operations";
    }

    @GetMapping("/articles")
    public String getArticlesPage(Model model) {
        List<Articles> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        return "articles";
    }

    @GetMapping("/balances")
    public String getBalancesPage(Model model) {
        List<Balance> balances = balanceService.findAll();
        model.addAttribute("balances", balances);
        return "balances";
    }

    @GetMapping("/addArticle")
    public String addArticlePage() {

        return "addArticle";
    }

    @PostMapping("/saveArticle")
    public String addArticle(@RequestParam String name) {


        Articles article = new Articles(name);
        articleService.save(article);
        return "redirect:/articles";
    }

    @GetMapping("/addOperation")
    public String addOperationPage(Model model) {

        List<Articles> articles = articleService.findAll();
        model.addAttribute("articles", articles);

        return "addOperation";
    }

    @PostMapping("/saveOperation")
    public String addOperation(
            @RequestParam Long article_id,
            @RequestParam Double debit,
            @RequestParam Double credit,
            @RequestParam String create_date) {

        Optional<Articles> article = articleService.findById(article_id);

        if(article.isPresent()) {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            try {
                Date date = format.parse(create_date);
                Operations operation = new Operations(article.get(), debit, credit, date);
                operationService.save(operation);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return "redirect:/operations";
    }

    @GetMapping("/editArticle")
    public String editArticlePage(Model model) {

        List<Articles> articles = articleService.findAll();
        model.addAttribute("articles", articles);

        return "editArticle";
    }

    @PostMapping("/saveEditArticle")
    public String editArticle(@RequestParam Long id,
                              @RequestParam String name) {

        Optional<Articles> article = articleService.findById(id);

        if(article.isPresent()) {

            article.get().setName(name);
            articleService.save(article.get());
        }

        return "redirect:/articles";
    }

    @PostMapping("/deleteArticle")
    public String deleteArticlePage(@RequestParam Long id) {

        Optional<Articles> article = articleService.findById(id);

        if(article.isPresent()) {

            List<Operations> operations = operationService.findAll();

            for (Operations operation : operations) {
                if(!operation.getArticle_id().getId().equals(id)) {
                    articleService.deleteById(id);
                    break;
                }
            }
        }

        return "redirect:/articles";
    }

    @GetMapping("/editOperation")
    public String editOperationPage(Model model) {

        List<Articles> articles = articleService.findAll();
        model.addAttribute("articles", articles);

        List<Operations> operations = operationService.findAll();
        model.addAttribute("operations", operations);
        return "editOperation";
    }

    @PostMapping("/saveEditOperation")
    public String editOperation(@RequestParam Long id,@RequestParam Long article_id,
                                @RequestParam Double debit, @RequestParam Double credit,
                                @RequestParam String create_date) {

        Optional<Operations> operation = operationService.findById(id);
        Optional<Articles> article = articleService.findById(article_id);

        if(operation.isPresent() && article.isPresent()) {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            try {
                Date date = format.parse(create_date);

                operation.get().setArticle_id(article.get());
                operation.get().setDebit(debit);
                operation.get().setCredit(credit);
                operation.get().setCreateDate(date);
                operationService.save(operation.get());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return "redirect:/operations";
    }

    @PostMapping("/deleteOperation")
    public String deleteOperationPage(@RequestParam Long id) {

        Optional<Operations> operations = operationService.findById(id);

        if(operations.isPresent()) {
            if(operations.get().getBalanceId() == null) {
                operations.get().setArticle_id(null);
                operationService.deleteById(id);
            }
        }

        return "redirect:/operations";
    }

    @GetMapping("/createBalance")
    public String createBalancePage() {

        return "createBalance";
    }

    @PostMapping("/saveBalance")
    public String createBalance(@RequestParam String createDateStart,
                                @RequestParam String createDateEnd) {

         try {
            List<Operations> operations =
                    operationService.findByCreate_dateBetween(
                            new SimpleDateFormat("yyyy-MM-dd").parse(createDateStart),
                            new SimpleDateFormat("yyyy-MM-dd").parse(createDateEnd));

            if(!operations.isEmpty()) {
                double debit    = 0.0;
                double credit   = 0.0;
                double amount   = 0.0;

                for(Operations operation : operations) {
                    debit += operation.getDebit();
                    credit += operation.getCredit();
                }
                amount = debit - credit;

                Balance balance = new Balance(
                        new SimpleDateFormat("yyyy-MM-dd").parse(createDateEnd),
                        debit,
                        credit,
                        amount);
                balanceService.save(balance);

                for (Operations operation : operations) {
                    operation.setBalanceId(balance);
                    operationService.save(operation);
                }
            }
            else {
                Balance balance = new Balance(
                        new SimpleDateFormat("yyyy-MM-dd").parse(createDateEnd),
                         0.0 , 0.0, 0.0);
                balanceService.save(balance);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "redirect:/balances";
    }

    @PostMapping("/deleteBalance")
    public String deleteBalancePage(@RequestParam Long id) {

        Optional<Balance> balance = balanceService.findById(id);

        if(balance.isPresent()) {

            List<Operations> operations =
                    operationService.findAllByBalanceId(balance.get());

            if(!operations.isEmpty()) {

                for (Operations operation : operations) {

                    operation.setBalanceId(null);
                    operationService.save(operation);
                }
            }

            balanceService.deleteById(id);
        }

        return "redirect:/balances";
    }
}
