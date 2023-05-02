# Costa Wine

---
![markdown_logo](https://www.pngall.com/wp-content/uploads/4/Red-Wine-Glass-PNG-File.png)

### Project description:
This is an application which supports authentication, adding wine to a shopping cart and ordering perfect wine to different
cities in Ukraine. Moreover, everyone can choose wine by its color, type, event (a party with friends, a present, etc) and find the wine
which perfectly pairs with some food (like cheese, meat, etc). In addition, a user can sort wine by price or popularity.
Also, the app supports registration for new customers and *CRUD* operations.

### Features:

---
- register or login as a user
- find wine by: color, type, event and pair with
- sort wine by: popularity, price
- find out something interesting about the wine
- add wine to the shopping cart
- fill in shipping details for order
- make the order for a gift
- choose a payment option
- complete an order;

### Project structure (3-layer architecture):

---
1. *DAO* - handle CRUD operations to database.
2. *Service* - there are all business logic.
3. *Controllers* - handle requests, call services and send responses.

### Used technologies and libraries:

---
- Java 17
- Git
- Apache Maven
- Apache Tomcat
- MySQL
- Hibernate, JPA
- Spring Boot
- Spring Security
- Spring Web
- Checkstyle plugin

### Instructions to run the project:

---
- Install [IDE](https://www.jetbrains.com/help/idea/installation-guide.html);
- Install [MySQL](https://dev.mysql.com/downloads/installer/);
- Clone the repo: [https://github.com/AntonKalenskiy/wine-project](https://github.com/AntonKalenskiy/wine-project);
- Configure [/src/main/resources/application.properties](./src/main/resources/application.properties) with your URL, USERNAME, PASSWORD, DRIVER-CLASS-NAME;
- Enjoy the application.

