Horse Racing
Final work for Epam training 2015-2016.

Theme: User can make different rates. Administrator must add new racing, determine quantity of win's sum, must fix racing result.

To build and run the code:
1. Install MySQL server (http://www.tutorialspoint.com/mysql/mysql-installation.htm).
2. Add new user for MySQL server with login = root, password = 135246.
3. Connect to MySQL server (login: root, password: 135246).
4. Perform script from the project root directory to create DB for MySQL server.
5. Run creatingWar.bat from the project root directory to building file webapp-1.0.0.war . (Need Maven. If you don’t have maven then install him (https://maven.apache.org/install.html )).
6. Deploy webapp-1.0.0.war (horse-racing-web\webapp\target\webapp-1.0.0.war) on web-server (f.e. Tomcat).

Connect attributes to DB can be corrected in app.properties (horse-racing-web\data\src\main\resources\ app.properties).
