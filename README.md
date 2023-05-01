# Costa Wine

---
![markdown_logo](https://www.pngall.com/wp-content/uploads/4/Red-Wine-Glass-PNG-File.png)

### Project description:
This is an application, which supports authentication, adding wine to shopping cart and ordering perfect wine to different
cities in Ukraine. More over everyone can choose wine by it`s color, type, event (party with friends or present) and find the wine,
witch is perfect pairs with some food (like cheese or meat for ex.). In additional user can sort wine by price or popularity.
Also, the application supports registration for new customers and *CRUD* operations.

### Features:

---
- register or login as user
- find wine by: color, type, event and pair with
- sort wine by: popularity, price
- get to know something interesting about wine
- add wine to the shopping cart
- fill shipping details for order
- make the order for gift
- choose variant of payment
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

```properties
spring_profiles_active=prod
PROD_DB_HOST=containers-us-west-201.railway.app
PROD_DB_PORT=5447
PROD_DB_NAME=railway
PROD_DB_PASSWORD=f9LhuRGYC30Tg5Pm4a01
PROD_DB_USERNAME=root
```