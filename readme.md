* [lista formulario](https://www.baeldung.com/thymeleaf-list)
* [manejo de fechas en thyme](https://www.baeldung.com/dates-in-thymeleaf)

````
<input type="hidden" th:field="*{blogId}" id="blogId">
````

````
    <!-- JavaScript to prevent form submission on Enter key -->
    <script>
        document.getElementById("myForm").addEventListener("keypress", function(event) {
            if (event.key === "Enter") {
                event.preventDefault();
            }
        });
    </script>
````

````
Page<ObjectDto> entities =
objectEntityRepository.findAll(pageable)
.map(ObjectDto::fromEntity);
````

* [Consultas JPA QUERY](https://www.baeldung.com/spring-data-jpa-query)
* [materia referencia ](https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html)
* [Formato Fecha](https://www.baeldung.com/spring-boot-formatting-json-dates)

## Videos Referencia
* [Luigi Code / spring boot](https://www.youtube.com/playlist?list=PL4bT56Uw3S4yTSw5Cg1-mhgoS85fVeFkT)
* [Luigi Code / android](https://www.youtube.com/playlist?list=PL4bT56Uw3S4zTO4Kls4m0wH2eNUQBXDfw)
* [object mapper](https://www.youtube.com/watch?v=jXQ4JJDLDQA)
* [Bottom navigation bar / android studio](https://www.youtube.com/watch?v=jOFLmKMOcK0&ab_channel=Foxandroid)
* [Thymeleaf con ModelAndView](https://www.youtube.com/watch?v=nP7O26fFkjI&list=WL&index=31&t=1s)
    * [data table](https://datatables.net/)
    * [jquery](https://www.w3schools.com/jquery/jquery_get_started.asp)
* [Thymealf Facturacion con sesion](https://www.youtube.com/watch?v=ey9vwgMTMDE&t=7s)
    * [pagina detalle](https://parzibyte.me/blog/2019/09/04/sistema-ventas-spring-mvc-mysql-bootstrap/)
    * Tipos de alert
        * .addFlashAttribute("clase", "warning");
        * .addFlashAttribute("clase", "success");
        * .addFlashAttribute("clase", "info");