package com.project.wineshop.config;

import com.project.wineshop.dto.request.ProductRequestDto;
import com.project.wineshop.model.ProductColor;
import com.project.wineshop.model.ProductEvent;
import com.project.wineshop.model.ProductType;
import com.project.wineshop.model.Role;
import com.project.wineshop.repository.ProductRepository;
import com.project.wineshop.repository.RoleRepository;
import com.project.wineshop.service.mapper.impl.ProductMapper;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Injector {
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Injector(RoleRepository roleRepository,
                    ProductRepository productRepository,
                    ProductMapper productMapper) {
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @PostConstruct
    public void inject() {
        if (roleRepository.findAll().isEmpty()) {
            Role userRole = new Role();
            userRole.setName(Role.RoleName.USER);
            roleRepository.save(userRole);

            Role adminRole = new Role();
            adminRole.setName(Role.RoleName.ADMIN);
            roleRepository.save(adminRole);

            Role guestRole = new Role();
            guestRole.setName(Role.RoleName.GUEST);
            roleRepository.save(guestRole);
        }
        if (productRepository.findAll().isEmpty()) {
            injectWinePink1();
            injectWinePink2();
            injectWinePink3();
            injectWinePink4();
            injectWinePink5();
            injectWinePink6();

            injectWineRed1();
            injectWineRed2();
            injectWineRed3();
            injectWineRed4();
            injectWineRed5();
            injectWineRed6();

            injectWineWhite1();
            injectWineWhite2();
            injectWineWhite3();
            injectWineWhite4();
            injectWineWhite5();
            injectWineWhite6();
        }
    }

    private void injectWinePink1() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Sauvignon Rosé");
        requestDto.setPrice(new BigDecimal("42.99"));
        requestDto.setType(ProductType.Type.SEMISWEET.name());
        requestDto.setColor(ProductColor.Color.PINK.name());
        requestDto.setEvent(ProductEvent.Event.FOR_PRESENT.name());
        requestDto.setPairing(
                "Perfect with pasta, tender meat, cheese, seafood, rice and also as an aperitif.");
        requestDto.setVintage(2020);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("100% Cabernet Sauvignon");
        requestDto.setTaste("Bright cherry-coloured rosé wine. Forms legs when swirled in the "
                + "glass. Intense and very pleasant first impression, with the fruity aromas that"
                + " are typical of the Cabernet Sauvignon variety. The aromas on the palate are "
                + "intense. It is very round, fresh and elegant, with notes of strawberry and "
                + "caramel. The finish is very long and complex. This is a very aromatic wine, "
                + "with a complex palate and a long aromatic finish, qualities that promise very "
                + "good development in the bottle.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setImageLink("https://s3.eu-north-1.amazonaws.com/wine-shop-images/products/pink1.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230421T075852Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604799&X-Amz-Credential=AKIAW3DYPUZUMGLJJDXC%2F20230421%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=30bc614b6f9c12c7f99e832ab26ce77dd41a1d347be013e889ad05fab1d1c2ab");
        List<String> wineDishPairing = List.of("Pasta", "Meat", "Cheese", "Seafood", "Rice");
        requestDto.setDishes(wineDishPairing);
        productRepository.save(productMapper.mapToModel(requestDto));
    }

    private void injectWinePink2() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Brut Reserva");
        requestDto.setPrice(new BigDecimal("6.99"));
        requestDto.setType(ProductType.Type.SEMISWEET.name());
        requestDto.setColor(ProductColor.Color.PINK.name());
        requestDto.setVintage(2020);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("Xarel·lo, Macabeu, Parellada");
        requestDto.setTaste("Red crianza wine of garnet red hue with hints of russet. First "
                + "impression is pleasant and refined. Varietal aromas and aromas from barrel"
                + "ageing can be detected. It is powerful, pleasant and direct on the palate, "
                + "with notes of vanilla and oak that do not mask the characteristics of the "
                + "grape varieties from which it is made. Good intensity and finish.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setEvent(ProductEvent.Event.PARTY_WITH_FRIENDS.name());
        requestDto.setPairing("Perfect as an aperitif and to accompany rice, fish, seafood and "
                + "braised white and red meat.");
        requestDto.setImageLink("https://s3.eu-north-1.amazonaws.com/wine-shop-images/products/pink2.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230421T080018Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604799&X-Amz-Credential=AKIAW3DYPUZUMGLJJDXC%2F20230421%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=e7528ad5f364d960ea5c5b805700c588ffc170386477abf4886869b63d1080d0");
        List<String> wineDishPairing = List.of("Rice", "Fish", "Seafood", "Meat");
        requestDto.setDishes(wineDishPairing);
        productRepository.save(productMapper.mapToModel(requestDto));
    }

    private void injectWinePink3() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Chardonnay Rosé");
        requestDto.setPrice(new BigDecimal("25.99"));
        requestDto.setType(ProductType.Type.SEMISWEET.name());
        requestDto.setColor(ProductColor.Color.PINK.name());
        requestDto.setVintage(2020);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("Xarel·lo, Macabeu, Parellada");
        requestDto.setTaste("Pale yellow with green reflections has a splendid effervescencia "
                + "with rosaries of very fine bubbles that form a crown. The nose is fresh, with"
                + "notes of peach and apricot. In the mouth it is round, very pleasant with a "
                + "finish reminiscent of ripe fruit and pastry.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_SPECIAL_EVENTS.name());
        requestDto.setPairing("Perfect with pasta, tender meat, cheese, seafood, rice and also as"
                + "an aperitif.");
        requestDto.setImageLink("https://s3.eu-north-1.amazonaws.com/wine-shop-images/products/pink3.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230421T080111Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604799&X-Amz-Credential=AKIAW3DYPUZUMGLJJDXC%2F20230421%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=5ee5eb90f7f5dcadb7e7faee82e5f601b30751fa655b81e8d9b014a92626c5a8");
        List<String> wineDishPairing = List.of("Pasta", "Meat", "Cheese", "Seafood", "Rice");
        requestDto.setDishes(wineDishPairing);
        productRepository.save(productMapper.mapToModel(requestDto));
    }

    private void injectWinePink4() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Rosé");
        requestDto.setPrice(new BigDecimal("45.99"));
        requestDto.setType(ProductType.Type.DRY.name());
        requestDto.setColor(ProductColor.Color.PINK.name());
        requestDto.setVintage(2019);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("Tempranillo, Merlot");
        requestDto.setTaste("Cherry-red rosé wine with crystalline aspect. Nice first impression "
                + "of fine fruity aromas. It's nice on the palate, fresh, good, balanced, round, "
                + "and with a good acidity. The after taste is elegant and fruity. It is an "
                + "harmonic Rosé wine , correct and with a medium persistence.");
        requestDto.setTemperature("Serve between 16-18ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_SPECIAL_EVENTS.name());
        requestDto.setPairing("Perfect with salads, chicken, rise, tender meats and barbeques.");
        requestDto.setImageLink("https://s3.eu-north-1.amazonaws.com/wine-shop-images/products/pink4.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230421T080152Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604799&X-Amz-Credential=AKIAW3DYPUZUMGLJJDXC%2F20230421%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=6900a9ec96b834ff195ffd746bdf9f1fe8aa36766edd2df1e7d11bc82a2aaa19");
        List<String> wineDishPairing = List.of("Salads", "Chicken", "Rice", "Meat", "Barbeques");
        requestDto.setDishes(wineDishPairing);
        productRepository.save(productMapper.mapToModel(requestDto));
    }

    private void injectWinePink5() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Chardonnay Rosé");
        requestDto.setPrice(new BigDecimal("57.99"));
        requestDto.setType(ProductType.Type.SEMISWEET.name());
        requestDto.setColor(ProductColor.Color.PINK.name());
        requestDto.setVintage(2020);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("Xarel·lo, Macabeu, Parellada");
        requestDto.setTaste("Pale yellow with green reflections has a splendid effervescencia "
                + "with rosaries of very fine bubbles that form a crown. The nose is fresh, with"
                + "notes of peach and apricot. In the mouth it is round, very pleasant with a "
                + "finish reminiscent of ripe fruit and pastry.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_LONELY_EVENING.name());
        requestDto.setPairing("Perfect with pasta, tender meat, cheese, seafood, rice and also "
                + "as an aperitif.");
        requestDto.setImageLink("https://s3.eu-north-1.amazonaws.com/wine-shop-images/products/pink5.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230421T080245Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604799&X-Amz-Credential=AKIAW3DYPUZUMGLJJDXC%2F20230421%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=bab78c9b49860f4d8c407a093efafb6d875187db455a0f9c52cad2c84857f0ae");
        List<String> wineDishPairing = List.of("Pasta", "Meat", "Cheese", "Seafood", "Rice");
        requestDto.setDishes(wineDishPairing);
        productRepository.save(productMapper.mapToModel(requestDto));
    }

    private void injectWinePink6() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Brut Rosé");
        requestDto.setPrice(new BigDecimal("48.99"));
        requestDto.setType(ProductType.Type.SEMISWEET.name());
        requestDto.setColor(ProductColor.Color.PINK.name());
        requestDto.setVintage(2020);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("100% Trepat");
        requestDto.setTaste("Lively and bright, intense pink hue. Good release of small bubbles "
                + "that rise to form a distinct crown. First impression: pleasant and powerful, "
                + "followed by aromas reminiscent of red berries. It is clean and sweet on the "
                + "palate, with a good, long-lasting finish. Balanced, full of flavour, and very "
                + "fruity.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_PRESENT.name());
        requestDto.setPairing("Perfect as an aperitif or dessert. It can, also, accompany mild "
                + "cheeses and rices.");
        requestDto.setImageLink("https://s3.eu-north-1.amazonaws.com/wine-shop-images/products/pink6.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230421T080327Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604799&X-Amz-Credential=AKIAW3DYPUZUMGLJJDXC%2F20230421%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=23cd0f1068f6b366da87ea87c71ff9ec4cadd95c319d94aea7bd84bc3a4e6bee");
        List<String> wineDishPairing = List.of("Cheese", "Rice");
        requestDto.setDishes(wineDishPairing);
        productRepository.save(productMapper.mapToModel(requestDto));
    }

    private void injectWineRed1() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Cabarnet Sauvignon");
        requestDto.setPrice(new BigDecimal("55.99"));
        requestDto.setType(ProductType.Type.SEMISWEET.name());
        requestDto.setColor(ProductColor.Color.RED.name());
        requestDto.setVintage(2016);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("100% Cabernet Sauvignon");
        requestDto.setTaste("Young red wine with an intense red hue flecked with purple. Forms "
                + "legs when swirled in the glass. Very pleasant first impression, very intense "
                + "and complex aromas which include notes of jam, ripe red fruit, and liquorice. "
                + "The aromas on the palate are distinctly varietal in character. The wine round,"
                + "unctuous, long-lingering, with sweet tannins that make for a long and "
                + "flavoursome finish. This is a powerful, pleasant wine. It has fruit and a very"
                + "long-lingering finish, qualities that promise very good development in the "
                + "bottle.");
        requestDto.setTemperature("Serve between 12-14ºC");
        requestDto.setEvent(ProductEvent.Event.PARTY_WITH_FRIENDS.name());
        requestDto.setPairing("Perfect with stewed meat, cured cheese, sausages, and dishes with "
                + "personality.");
        requestDto.setImageLink("https://s3.eu-north-1.amazonaws.com/wine-shop-images/products/red1.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230421T080447Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604799&X-Amz-Credential=AKIAW3DYPUZUMGLJJDXC%2F20230421%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=a8505994b675ecbdab03c74ea4dc36ed9a756063349ba393f04ad8e4ba889637");
        List<String> wineDishPairing = List.of("Meat", "Cheese", "Sausages");
        requestDto.setDishes(wineDishPairing);
        productRepository.save(productMapper.mapToModel(requestDto));
    }

    private void injectWineRed2() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Sauvignon Barrel");
        requestDto.setPrice(new BigDecimal("47.95"));
        requestDto.setType(ProductType.Type.SEMISWEET.name());
        requestDto.setColor(ProductColor.Color.RED.name());
        requestDto.setVintage(2019);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("100% Merlot");
        requestDto.setTaste("Young red wine with an intense red hue flecked with purple. Forms "
                + "legs when swirled in the glass. Very pleasant first impression, very intense "
                + "and complex aromas which include notes of jam, ripe red fruit, and liquorice. "
                + "The aromas on the palate are distinctly varietal in character. The wine round, "
                + "unctuous, long-lingering, with sweet tannins that make for a long and "
                + "flavoursome finish. This is a powerful, pleasant wine. It has fruit and a very "
                + "long-lingering finish, qualities that promise very good development in the "
                + "bottle.");
        requestDto.setTemperature("Serve between 12-14ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_PRESENT.name());
        requestDto.setPairing("Combine this wine with risottos, cheese, sausages, poultry, and "
                + "stewed meats.");
        requestDto.setImageLink("https://s3.eu-north-1.amazonaws.com/wine-shop-images/products/red2.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230421T080525Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604799&X-Amz-Credential=AKIAW3DYPUZUMGLJJDXC%2F20230421%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=c09d8b8bd56a879af19d09d737a7a49c5ac2bfcebeea083f60ce601684888b94");
        List<String> wineDishPairing = List.of("Risottos", "Cheese", "Sausages", "Poultry", "Meat");
        requestDto.setDishes(wineDishPairing);
        productRepository.save(productMapper.mapToModel(requestDto));
    }

    private void injectWineRed3() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Merlot Barrel");
        requestDto.setPrice(new BigDecimal("42.99"));
        requestDto.setType(ProductType.Type.SEMISWEET.name());
        requestDto.setColor(ProductColor.Color.RED.name());
        requestDto.setVintage(2019);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("100% Merlot");
        requestDto.setTaste("Red crianza wine of garnet red hue with hints of russet. First "
                + "impression is pleasant and refined. Varietal aromas and aromas from barrel "
                + "ageing can be detected. It is powerful, pleasant and direct on the palate, "
                + "with notes of vanilla and oak that do not mask the characteristics of the grape"
                + "varieties from which it is made. Good intensity and finish.");
        requestDto.setTemperature("Serve between 16-18ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_SPECIAL_EVENTS.name());
        requestDto.setPairing("Combine this wine with risottos, cheese, sausages, poultry, and "
                + "stewed meats.");
        requestDto.setImageLink("https://s3.eu-north-1.amazonaws.com/wine-shop-images/products/red3.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230421T080603Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604799&X-Amz-Credential=AKIAW3DYPUZUMGLJJDXC%2F20230421%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=c4792dbc40900c8e5b0e795d874907f0d2cec9568e85aa2fdbed13783858613a");
        List<String> wineDishPairing = List.of("Risottos", "Cheese", "Sausages", "Poultry", "Meat");
        requestDto.setDishes(wineDishPairing);
        productRepository.save(productMapper.mapToModel(requestDto));
    }

    private void injectWineRed4() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Cabernet");
        requestDto.setPrice(new BigDecimal("77.99"));
        requestDto.setType(ProductType.Type.DRY.name());
        requestDto.setColor(ProductColor.Color.RED.name());
        requestDto.setVintage(2018);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("100% Cabernet Sauvignon");
        requestDto.setTaste("Red crianza wine of garnet red hue with hints of russet. First "
                + "impression is pleasant with a nice aroma. Powerful and complex, has some "
                + "spices and varietal aromas, rounded because of the oak. In the mouth is warm, "
                + "good tannin presence but balanced with the acidity. With round body and a long "
                + "and tasty after taste. Well-balanced, powerful, full body, persistent, and nice"
                + " tannin balance.");
        requestDto.setTemperature("Serve between 16-18ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_SPECIAL_EVENTS.name());
        requestDto.setPairing("This wine is perfect with cured cheeses, red meats, sausages and "
                + "strong dishes.");
        requestDto.setImageLink("https://s3.eu-north-1.amazonaws.com/wine-shop-images/products/red4.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230421T080652Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604799&X-Amz-Credential=AKIAW3DYPUZUMGLJJDXC%2F20230421%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=04933c74633a1e2afc10fd82ec78cf7752ba0ee7e25475f8eadebb1982434ac6");
        List<String> wineDishPairing = List.of("Cheese", "Meat", "Sausages", "Strong dishes");
        requestDto.setDishes(wineDishPairing);
        productRepository.save(productMapper.mapToModel(requestDto));
    }

    private void injectWineRed5() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Chardonnay");
        requestDto.setPrice(new BigDecimal("62.99"));
        requestDto.setType(ProductType.Type.DRY.name());
        requestDto.setColor(ProductColor.Color.RED.name());
        requestDto.setVintage(2020);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("Xarel·lo, Macabeu, Parellada");
        requestDto.setTaste("Red crianza wine of garnet red hue with hints of russet. First "
                + "impression is pleasant with a nice aroma. Powerful and complex, has some "
                + "spices and varietal aromas, rounded because of the oak. In the mouth is "
                + "warm, good tannin presence but balanced with the acidity. With round body and "
                + "a long and tasty after taste. Well-balanced, powerful, full body, persistent, "
                + "and nice tannin balance.");
        requestDto.setTemperature("Serve between 16-18ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_PRESENT.name());
        requestDto.setPairing("This wine is perfect with cured cheeses, red meats, sausages and "
                + "strong dishes.");
        requestDto.setImageLink("https://s3.eu-north-1.amazonaws.com/wine-shop-images/products/red5.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230421T080735Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604799&X-Amz-Credential=AKIAW3DYPUZUMGLJJDXC%2F20230421%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=013072a41562fd4b718b7d50dc0f4f8f9d7a7232bac6d1af2a323c0ee37ec924");
        List<String> wineDishPairing = List.of("Cheese", "Meat", "Sausages", "Strong dishes");
        requestDto.setDishes(wineDishPairing);
        productRepository.save(productMapper.mapToModel(requestDto));
    }

    private void injectWineRed6() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Cabarnet Sauvignon");
        requestDto.setPrice(new BigDecimal("55.99"));
        requestDto.setType(ProductType.Type.SEMISWEET.name());
        requestDto.setColor(ProductColor.Color.RED.name());
        requestDto.setVintage(2016);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("100% Cabernet Sauvignon");
        requestDto.setTaste("Young red wine with an intense red hue flecked with purple. Forms "
                + "legs when swirled in the glass. Very pleasant first impression, very intense "
                + "and complex aromas which include notes of jam, ripe red fruit, and liquorice. "
                + "The aromas on the palate are distinctly varietal in character. The wine round, "
                + "unctuous, long-lingering, with sweet tannins that make for a long and "
                + "flavoursome finish. This is a powerful, pleasant wine. It has fruit and a very "
                + "long-lingering finish, qualities that promise very good development in the "
                + "bottle.");
        requestDto.setTemperature("Serve between 12-14ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_LONELY_EVENING.name());
        requestDto.setPairing("Perfect with stewed meat, cured cheese, sausages, and dishes with "
                + "personality.");
        requestDto.setImageLink("https://s3.eu-north-1.amazonaws.com/wine-shop-images/products/red6.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230421T080813Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604799&X-Amz-Credential=AKIAW3DYPUZUMGLJJDXC%2F20230421%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=ff57f5b13dfaaaf23559ab84ef52f72c3f07b55941d62cf429022df5d83ce9ca");
        List<String> wineDishPairing = List.of("Meat", "Cheese", "Sausages",
                "Dishes with personality");
        requestDto.setDishes(wineDishPairing);
        productRepository.save(productMapper.mapToModel(requestDto));
    }

    private void injectWineWhite1() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Dry White");
        requestDto.setPrice(new BigDecimal("55.99"));
        requestDto.setType(ProductType.Type.DRY.name());
        requestDto.setColor(ProductColor.Color.WHITE.name());
        requestDto.setVintage(2016);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("100% Xarel·lo");
        requestDto.setTaste("Pale yellow white wine lightly flecked with green. Forms legs when "
                + "swirled in the glass. Very pleasant upon first impression, with intense aromas "
                + "reminiscent of white flowers with hints of ripe fruit. The aromas carry over "
                + "to the palate, which is distinctly fruity in character, with the Xarel·lo "
                + "characteristics coming through in the tasting. On the palate, the wine is dry, "
                + "fresh, and flavoursome, with good acidity. This wine has a significant "
                + "aromatic potential, with fruit on the palate and a lingering and very pleasant "
                + "finish, qualities that ensure good development in the bottle.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setEvent(ProductEvent.Event.PARTY_WITH_FRIENDS.name());
        requestDto.setPairing("Perfect with fish, mild cheese, rice and white meats.");
        requestDto.setImageLink("https://s3.eu-north-1.amazonaws.com/wine-shop-images/products/white1.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230421T080908Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604799&X-Amz-Credential=AKIAW3DYPUZUMGLJJDXC%2F20230421%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=5f6ca1718e4bd8e04fa7a5a82a53e39475eb861482a2fa18fa7e82d98161f0fe");
        List<String> wineDishPairing = List.of("Fish", "Cheese", "Rice", "Meat");
        requestDto.setDishes(wineDishPairing);
        productRepository.save(productMapper.mapToModel(requestDto));
    }

    private void injectWineWhite2() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Chardonnay Barrel");
        requestDto.setPrice(new BigDecimal("29.99"));
        requestDto.setType(ProductType.Type.DRY.name());
        requestDto.setColor(ProductColor.Color.WHITE.name());
        requestDto.setVintage(2017);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("Xarel·lo, Macabeu, Parellada");
        requestDto.setTaste("White wine with a bright yellow hue flecked with gold. Very pleasant "
                + "first impression, with subtle vanilla and smoky aromas as well as floral and "
                + "honey notes. It is warm, creamy, and very pleasant on the palate. Fermentation "
                + "and ageing of the lees have given this wine personality");
        requestDto.setTemperature("Serve between 10-12ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_LONELY_EVENING.name());
        requestDto.setPairing("Perfect for all types of aperitifs, fish, rice, smoked dishes, "
                + "white meat and cheese.");
        requestDto.setImageLink("https://s3.eu-north-1.amazonaws.com/wine-shop-images/products/white2.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230421T080952Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604799&X-Amz-Credential=AKIAW3DYPUZUMGLJJDXC%2F20230421%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=9a4e1e469d58afc6faabe71321bf71f5b22fc1724865cf0adf86ed9c44646cc6");
        List<String> wineDishPairing = List.of("Fish", "Rice", "Smoked dishes", "Meat", "Cheese");
        requestDto.setDishes(wineDishPairing);
        productRepository.save(productMapper.mapToModel(requestDto));
    }

    private void injectWineWhite3() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("White Semi Sweet");
        requestDto.setPrice(new BigDecimal("85.95"));
        requestDto.setType(ProductType.Type.SEMISWEET.name());
        requestDto.setColor(ProductColor.Color.WHITE.name());
        requestDto.setVintage(2015);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("Xarel·lo, Macabeu, Parellada");
        requestDto.setTaste("Pale yellow white wine with a bright, clear appearance. Powerful "
                + "and pleasant first impression, with complex, predominant fruity aromas. It is "
                + "sweet, fresh and round on the palate, with a fruity taste and a lingering "
                + "bouquet. Aromatic, with a fruity palate, character and a long-lingering "
                + "finish.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_SPECIAL_EVENTS.name());
        requestDto.setPairing("Combine this wine with risottos, cheese, sausages, poultry, and "
                + "stewed meats.");
        requestDto.setImageLink("https://s3.eu-north-1.amazonaws.com/wine-shop-images/products/white3.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230421T081039Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604799&X-Amz-Credential=AKIAW3DYPUZUMGLJJDXC%2F20230421%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=e8d2edcc43d3c77dc12dead62ff3b3a8dce145d5547e37da44bf2cb12056f4aa");
        List<String> wineDishPairing = List.of("Risottos", "Cheese", "Sausages", "Poultry", "Meat");
        requestDto.setDishes(wineDishPairing);
        productRepository.save(productMapper.mapToModel(requestDto));
    }

    private void injectWineWhite4() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Ice");
        requestDto.setPrice(new BigDecimal("29.99"));
        requestDto.setType(ProductType.Type.DRY.name());
        requestDto.setColor(ProductColor.Color.WHITE.name());
        requestDto.setVintage(2018);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("100% Cabernet Sauvignon");
        requestDto.setTaste("Pale yellow white wine with a bright, clear appearance. Powerful and "
                + "pleasant first impression, with complex, predominant fruity aromas. It is "
                + "sweet, fresh and round on the palate, with a fruity taste and a lingering "
                + "bouquet. Aromatic, with a fruity palate, character and a long-lingering "
                + "finish.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setEvent(ProductEvent.Event.PARTY_WITH_FRIENDS.name());
        requestDto.setPairing("This wine is perfect with cured cheeses, red meats, sausages and "
                + "strong dishes.");
        requestDto.setImageLink("https://s3.eu-north-1.amazonaws.com/wine-shop-images/products/white4.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230421T081127Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604799&X-Amz-Credential=AKIAW3DYPUZUMGLJJDXC%2F20230421%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=8eaaa17749e249d9f21ed5fa629f3cb8bdfae8ec2360366fe71a27c155887757");
        List<String> wineDishPairing = List.of("Cheese", "Meat", "Sausages", "Strong dishes");
        requestDto.setDishes(wineDishPairing);
        productRepository.save(productMapper.mapToModel(requestDto));
    }

    private void injectWineWhite5() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Chardonnay");
        requestDto.setPrice(new BigDecimal("62.99"));
        requestDto.setType(ProductType.Type.DRY.name());
        requestDto.setColor(ProductColor.Color.WHITE.name());
        requestDto.setVintage(2020);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("Chardonnay, Xarel·lo, Macabeu");
        requestDto.setTaste("Pale yellow flecked with gold, with a steady stream of small bubbles "
                + "forming beads. First impression: good, powerful and refined; aromas from "
                + "bottle-ageing, as well as ripe fruit and flowers, can be detected. It is "
                + "round, pleasant, long-lingering and fresh on the palate. Well-balanced, "
                + "harmonious, direct, dry, with a long-lingering and pleasant finish.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_SPECIAL_EVENTS.name());
        requestDto.setPairing("Perfect for salads, vegetables, fishes and mild cheeses. It can "
                + "also be taken as an aperitif.");
        requestDto.setImageLink("https://s3.eu-north-1.amazonaws.com/wine-shop-images/products/white5.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230421T081204Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604799&X-Amz-Credential=AKIAW3DYPUZUMGLJJDXC%2F20230421%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=4703cb54bc9026b60952f93f79ec72b66068e4eec088fe4f8b79127efe091638");
        List<String> wineDishPairing = List.of("Salads", "Vegetables", "Fish", "Cheese");
        requestDto.setDishes(wineDishPairing);
        productRepository.save(productMapper.mapToModel(requestDto));
    }

    private void injectWineWhite6() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Reserva Especial");
        requestDto.setPrice(new BigDecimal("55.99"));
        requestDto.setType(ProductType.Type.SEMISWEET.name());
        requestDto.setColor(ProductColor.Color.WHITE.name());
        requestDto.setVintage(2016);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("Chardonnay, Xarel·lo, Macabeu");
        requestDto.setTaste("Pale yellow white wine lightly flecked with green. Forms legs when "
                + "swirled in the glass. Very pleasant upon first impression, with intense aromas "
                + "reminiscent of white flowers with hints of ripe fruit. The aromas carry over "
                + "to the palate, which is distinctly fruity in character, with the Xarel·lo "
                + "characteristics coming through in the tasting. On the palate, the wine is dry, "
                + "fresh, and flavoursome, with good acidity. This wine has a significant "
                + "aromatic potential, with fruit on the palate and a lingering and very pleasant "
                + "finish, qualities that ensure good development in the bottle.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_PRESENT.name());
        requestDto.setPairing("The great personality: stewed meats and fish, cheese, foie, and "
                + "smoked foods");
        requestDto.setImageLink("https://s3.eu-north-1.amazonaws.com/wine-shop-images/products/white6.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230421T081242Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604799&X-Amz-Credential=AKIAW3DYPUZUMGLJJDXC%2F20230421%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=0965929e07e16805667d93916aa2dc75275daceb7440c84a084269acf263847b");
        List<String> wineDishPairing = List.of("Meat", "Fish", "Cheese", "Foie", "Smoked dishes");
        requestDto.setDishes(wineDishPairing);
        productRepository.save(productMapper.mapToModel(requestDto));
    }
}
