
Change encoding 'windows-1251'

iCoffee - simple JPA-JSF project



Необходимое окружение:

- MySql - реляционная система управления базами данных (испльзовалась версия 5.6.29)
- Apache Tomcat - контейнер сервлетов (испльзовалась версия 9.0.0)



Для запуска приложения необходимо выполнить следующие шаги:

1. С помощью скрипта schemaDB из папки \iCoffee\src\main\resources\db\ создайте базу данных для приложения (имеет первоначальное заполнение).
2. Внесите необходимы изменения в конфигурационный файл hibernate.cfg.xml из папки \iCoffee\src\main\resources\.
3. Запустите скрипт makeWar.bat при этом в папке iCoffee\target\ появится файл с расширением icoffee.war.
4. Полученный на предыдущем шаге файл icoffee.war переместите в каталог Tomcat\webapps\ вашего Tomcat.
5. Запустите файл Tomcat\bin\startup.bat вашего Tomcat (если ранее он не был запущен).
6. После этого тестовое приложение станет доступно по адресу http://localhost:8080/icoffee/


Текст задания можно прочесть в файле \iCoffee\CoffeeShop JSF Rest call.txt