package com.example.obspringbootbuilds;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${app.message}")
    private String message;
    @Value("${app.varexample}")
    private String varEntorno;
    @GetMapping("hola")
    public String helloWorld() {
        System.getenv().forEach(
                (key, value) -> System.out.println(key+ " " + value)
        );
        return message+"\n"+varEntorno+"\nHello world... whats up! 1... 2... 3... probando...\nHello World... whats up!";
    }
    @GetMapping("bootstrap")
    public String otraURL() {
        return """
                <!doctype html>
                <html lang="en">
                  <head>
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <title>Bootstrap demo</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
                  </head>
                  <body>
                    <h1>Hello, world!</h1>
                    <a class="btn btn-primary" href="https://www.google.com"> Google </a>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
                  </body>
                </html>
                """;
    }
}
