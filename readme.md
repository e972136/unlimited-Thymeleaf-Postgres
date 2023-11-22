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