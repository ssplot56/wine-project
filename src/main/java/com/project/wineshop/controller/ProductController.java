package com.project.wineshop.controller;

import com.project.wineshop.dto.request.ProductRequestDto;
import com.project.wineshop.dto.response.ProductResponseDto;
import com.project.wineshop.model.Product;
import com.project.wineshop.model.WineDishPairing;
import com.project.wineshop.model.enums.ProductColor;
import com.project.wineshop.model.enums.ProductEvent;
import com.project.wineshop.model.Dish;
import com.project.wineshop.model.enums.ProductType;
import com.project.wineshop.service.DishService;
import com.project.wineshop.service.ProductService;
import com.project.wineshop.service.WineDishPairingService;
import com.project.wineshop.service.mapper.impl.ProductMapper;
import com.project.wineshop.utility.ImageReader;
import com.project.wineshop.utility.UrlParser;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final DishService dishService;
    private final WineDishPairingService wineDishPairingService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService,
                             DishService dishService,
                             WineDishPairingService wineDishPairingService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.dishService = dishService;
        this.wineDishPairingService = wineDishPairingService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody @Valid ProductRequestDto requestDto) {
        Product product = productMapper.mapToModel(requestDto);
        Product savedProduct = productService.save(product);
        return ResponseEntity.ok(productMapper.mapToDto(savedProduct));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll(@RequestParam (defaultValue = "20") Integer size,
                                                           @RequestParam (defaultValue = "0") Integer page,
                                                           @RequestParam (defaultValue = "id") String sortBy) {
        PageRequest pageRequest = UrlParser.formPageRequest(page, size, sortBy);
        List<ProductResponseDto> responseDtos = productService.findAll(pageRequest)
                .stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getById(@PathVariable("productId") @Positive Long productId) {
        Product product = productService.getById(productId);
        ProductResponseDto responseDto = productMapper.mapToDto(product);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable("id") @Positive Long id,
                                                     @RequestBody @Valid ProductRequestDto requestDto) {
        Product updatedProduct = productService.update(id, productMapper.mapToModel(requestDto));
        ProductResponseDto updatedDto = productMapper.mapToDto(updatedProduct);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{productId}")
    public void deleteById(@PathVariable("productId") @Positive Long productId) {
        productService.deleteById(productId);
    }

    @GetMapping("/inject")
    public String inject(@RequestParam(defaultValue = "100") Integer count) {
        injectDishes();

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

        return "Created 18 products";
    }

    private void injectDishes() {
        List<String> names = new ArrayList<>();
        names.add("Pasta");
        names.add("Meat");
        names.add("Cheese");
        names.add("Seafood");
        names.add("Rice");
        names.add("Fish");
        names.add("Salads");
        names.add("Chicken");
        names.add("Barbeques");
        names.add("Sausages");
        names.add("Poultry");
        names.add("Risottos");
        names.add("Strong dishes");
        names.add("Dishes with personality");
        names.add("Smoked dishes");
        names.add("Vegetables");
        names.add("Foie");

        for (String name : names) {
            Dish dish = new Dish();
            dish.setName(name);
            dishService.save(dish);
        }
    }

    private void injectWinePink1() {
        Product product = new Product();
        product.setName("Sauvignon Rosé");
        product.setPrice(new BigDecimal("42.99"));
        product.setType(ProductType.Type.SEMISWEET);
        product.setColor(ProductColor.Color.PINK);
        product.setEvent(ProductEvent.Event.FOR_PRESENT);
        product.setPairing("Perfect with pasta, tender meat, cheese, seafood, rice and also as an aperitif.");
        product.setVintage(2020);
        product.setCountry("Italy");
        product.setRegion("Toscana");
        product.setGrape("100% Cabernet Sauvignon");
        product.setTaste("Bright cherry-coloured rosé wine. Forms legs when swirled in the glass. Intense and very pleasant first impression, with the fruity aromas that are typical of the Cabernet Sauvignon variety. The aromas on the palate are intense. It is very round, fresh and elegant, with notes of strawberry and caramel. The finish is very long and complex. This is a very aromatic wine, with a complex palate and a long aromatic finish, qualities that promise very good development in the bottle.");
        product.setTemperature("Serve between 6-8ºC");
        product.setImage(ImageReader.getImageBytes("images/products/pink1.png"));
        productService.save(product);
        List<String> wineDishPairing = List.of("Pasta", "Meat", "Cheese", "Seafood", "Rice");
        injectWineDishPairing(1L, wineDishPairing);
    }

    private void injectWinePink2() {
        Product product = new Product();
        product.setName("Brut Reserva");
        product.setPrice(new BigDecimal("6.99"));
        product.setType(ProductType.Type.SEMISWEET);
        product.setColor(ProductColor.Color.PINK);
        product.setVintage(2020);
        product.setCountry("Italy");
        product.setRegion("Toscana");
        product.setGrape("Xarel·lo, Macabeu, Parellada");
        product.setTaste("Red crianza wine of garnet red hue with hints of russet. First impression is pleasant and refined. Varietal aromas and aromas from barrel ageing can be detected. It is powerful, pleasant and direct on the palate, with notes of vanilla and oak that do not mask the characteristics of the grape varieties from which it is made. Good intensity and finish.");
        product.setTemperature("Serve between 6-8ºC");
        product.setEvent(ProductEvent.Event.PARTY_WITH_FRIENDS);
        product.setPairing("Perfect as an aperitif and to accompany rice, fish, seafood and braised white and red meat.");
        product.setImage(ImageReader.getImageBytes("images/products/pink2.png"));
        productService.save(product);
        List<String> wineDishPairing = List.of("Rice", "Fish", "Seafood", "Meat");
        injectWineDishPairing(2L, wineDishPairing);
    }

    private void injectWinePink3() {
        Product product = new Product();
        product.setName("Chardonay Rosé");
        product.setPrice(new BigDecimal("25.99"));
        product.setType(ProductType.Type.SEMISWEET);
        product.setColor(ProductColor.Color.PINK);
        product.setVintage(2020);
        product.setCountry("Italy");
        product.setRegion("Toscana");
        product.setGrape("Xarel·lo, Macabeu, Parellada");
        product.setTaste("Pale yellow with green reflections has a splendid effervescencia with rosaries of very fine bubbles that form a crown. The nose is fresh, with notes of peach and apricot. In the mouth it is round, very pleasant with a finish reminiscent of ripe fruit and pastry.");
        product.setTemperature("Serve between 6-8ºC");
        product.setEvent(ProductEvent.Event.FOR_SPECIAL_EVENTS);
        product.setPairing("Perfect with pasta, tender meat, cheese, seafood, rice and also as an aperitif.");
        product.setImage(ImageReader.getImageBytes("images/products/pink3.png"));
        productService.save(product);
        List<String> wineDishPairing = List.of("Pasta", "Meat", "Cheese", "Seafood", "Rice");
        injectWineDishPairing(3L, wineDishPairing);
    }

    private void injectWinePink4() {
        Product product = new Product();
        product.setName("Rosé");
        product.setPrice(new BigDecimal("45.99"));
        product.setType(ProductType.Type.DRY);
        product.setColor(ProductColor.Color.PINK);
        product.setVintage(2019);
        product.setCountry("Italy");
        product.setRegion("Toscana");
        product.setGrape("Tempranillo, Merlot");
        product.setTaste("Cherry-red rosé wine with crystalline aspect. Nice first impression of fine fruity aromas. It's nice on the palate, fresh, good, balanced, round, and with a good acidity. The after taste is elegant and fruity. It is an harmonic Rosé wine , correct and with a medium persistence.");
        product.setTemperature("Serve between 16-18ºC");
        product.setEvent(ProductEvent.Event.FOR_SPECIAL_EVENTS);
        product.setPairing("Perfect with salads, chicken, rise, tender meats and barbeques.");
        product.setImage(ImageReader.getImageBytes("images/products/pink4.png"));
        productService.save(product);
        List<String> wineDishPairing = List.of("Salads", "Chicken", "Rice", "Meat", "Barbeques");
        injectWineDishPairing(4L, wineDishPairing);
    }

    private void injectWinePink5() {
        Product product = new Product();
        product.setName("Chardonay Rosé");
        product.setPrice(new BigDecimal("57.99"));
        product.setType(ProductType.Type.SEMISWEET);
        product.setColor(ProductColor.Color.PINK);
        product.setVintage(2020);
        product.setCountry("Italy");
        product.setRegion("Toscana");
        product.setGrape("Xarel·lo, Macabeu, Parellada");
        product.setTaste("Pale yellow with green reflections has a splendid effervescencia with rosaries of very fine bubbles that form a crown. The nose is fresh, with notes of peach and apricot. In the mouth it is round, very pleasant with a finish reminiscent of ripe fruit and pastry.");
        product.setTemperature("Serve between 6-8ºC");
        product.setEvent(ProductEvent.Event.FOR_LONELY_EVENING);
        product.setPairing("Perfect with pasta, tender meat, cheese, seafood, rice and also as an aperitif.");
        product.setImage(ImageReader.getImageBytes("images/products/pink1.png"));
        productService.save(product);
        List<String> wineDishPairing = List.of("Pasta", "Meat", "Cheese", "Seafood", "Rice");
        injectWineDishPairing(5L, wineDishPairing);
    }

    private void injectWinePink6() {
        Product product = new Product();
        product.setName("Brut Rosé");
        product.setPrice(new BigDecimal("48.99"));
        product.setType(ProductType.Type.SEMISWEET);
        product.setColor(ProductColor.Color.PINK);
        product.setVintage(2020);
        product.setCountry("Italy");
        product.setRegion("Toscana");
        product.setGrape("100% Trepat");
        product.setTaste("Lively and bright, intense pink hue. Good release of small bubbles that rise to form a distinct crown. First impression: pleasant and powerful, followed by aromas reminiscent of red berries. It is clean and sweet on the palate, with a good, long-lasting finish. Balanced, full of flavour, and very fruity.");
        product.setTemperature("Serve between 6-8ºC");
        product.setEvent(ProductEvent.Event.FOR_PRESENT);
        product.setPairing("Perfect as an aperitif or dessert. It can, also, accompany mild cheeses and rices.");
        product.setImage(ImageReader.getImageBytes("images/products/pink6.png"));
        productService.save(product);
        List<String> wineDishPairing = List.of("Cheese", "Rice");
        injectWineDishPairing(6L, wineDishPairing);
    }


    private void injectWineRed1() {
        Product product = new Product();
        product.setName("Cabarnet Sauvignon");
        product.setPrice(new BigDecimal("55.99"));
        product.setType(ProductType.Type.SEMISWEET);
        product.setColor(ProductColor.Color.RED);
        product.setVintage(2016);
        product.setCountry("Italy");
        product.setRegion("Toscana");
        product.setGrape("100% Cabernet Sauvignon");
        product.setTaste("Young red wine with an intense red hue flecked with purple. Forms legs when swirled in the glass. Very pleasant first impression, very intense and complex aromas which include notes of jam, ripe red fruit, and liquorice. The aromas on the palate are distinctly varietal in character. The wine round, unctuous, long-lingering, with sweet tannins that make for a long and flavoursome finish. This is a powerful, pleasant wine. It has fruit and a very long-lingering finish, qualities that promise very good development in the bottle.");
        product.setTemperature("Serve between 12-14ºC");
        product.setEvent(ProductEvent.Event.PARTY_WITH_FRIENDS);
        product.setPairing("Perfect with stewed meat, cured cheese, sausages, and dishes with personality.");
        product.setImage(ImageReader.getImageBytes("images/products/red1.png"));
        productService.save(product);
        List<String> wineDishPairing = List.of("Meat", "Cheese", "Sausages");
        injectWineDishPairing(7L, wineDishPairing);
    }

    private void injectWineRed2() {
        Product product = new Product();
        product.setName("Sauvignon Barrel");
        product.setPrice(new BigDecimal("47.95"));
        product.setType(ProductType.Type.SEMISWEET);
        product.setColor(ProductColor.Color.RED);
        product.setVintage(2019);
        product.setCountry("Italy");
        product.setRegion("Toscana");
        product.setGrape("100% Merlot");
        product.setTaste("Young red wine with an intense red hue flecked with purple. Forms legs when swirled in the glass. Very pleasant first impression, very intense and complex aromas which include notes of jam, ripe red fruit, and liquorice. The aromas on the palate are distinctly varietal in character. The wine round, unctuous, long-lingering, with sweet tannins that make for a long and flavoursome finish. This is a powerful, pleasant wine. It has fruit and a very long-lingering finish, qualities that promise very good development in the bottle.");
        product.setTemperature("Serve between 12-14ºC");
        product.setEvent(ProductEvent.Event.FOR_PRESENT);
        product.setPairing("Combine this wine with risottos, cheese, sausages, poultry, and stewed meats.");
        product.setImage(ImageReader.getImageBytes("images/products/red2.png"));
        productService.save(product);
        List<String> wineDishPairing = List.of("Risottos", "Cheese", "Sausages", "Poultry", "Meat");
        injectWineDishPairing(8L, wineDishPairing);
    }

    private void injectWineRed3() {
        Product product = new Product();
        product.setName("Merlot Barrel");
        product.setPrice(new BigDecimal("42.99"));
        product.setType(ProductType.Type.SEMISWEET);
        product.setColor(ProductColor.Color.RED);
        product.setVintage(2019);
        product.setCountry("Italy");
        product.setRegion("Toscana");
        product.setGrape("100% Merlot");
        product.setTaste("Red crianza wine of garnet red hue with hints of russet. First impression is pleasant and refined. Varietal aromas and aromas from barrel ageing can be detected. It is powerful, pleasant and direct on the palate, with notes of vanilla and oak that do not mask the characteristics of the grape varieties from which it is made. Good intensity and finish.");
        product.setTemperature("Serve between 16-18ºC");
        product.setEvent(ProductEvent.Event.FOR_SPECIAL_EVENTS);
        product.setPairing("Combine this wine with risottos, cheese, sausages, poultry, and stewed meats.");
        product.setImage(ImageReader.getImageBytes("images/products/red3.png"));
        productService.save(product);
        List<String> wineDishPairing = List.of("Risottos", "Cheese", "Sausages", "Poultry", "Meat");
        injectWineDishPairing(9L, wineDishPairing);
    }

    private void injectWineRed4() {
        Product product = new Product();
        product.setName("Cabernet");
        product.setPrice(new BigDecimal("77.99"));
        product.setType(ProductType.Type.DRY);
        product.setColor(ProductColor.Color.RED);
        product.setVintage(2018);
        product.setCountry("Italy");
        product.setRegion("Toscana");
        product.setGrape("100% Cabernet Sauvignon");
        product.setTaste("Red crianza wine of garnet red hue with hints of russet. First impression is pleasant with a nice aroma. Powerful and complex, has some spices and varietal aromas, rounded because of the oak. In the mouth is warm, good tannin presence but balanced with the acidity. With round body and a long and tasty after taste. Well-balanced, powerful, full body, persistent, and nice tannin balance.");
        product.setTemperature("Serve between 16-18ºC");
        product.setEvent(ProductEvent.Event.FOR_SPECIAL_EVENTS);
        product.setPairing("This wine is perfect with cured cheeses, red meats, sausages and strong dishes.");
        product.setImage(ImageReader.getImageBytes("images/products/red4.png"));
        productService.save(product);
        List<String> wineDishPairing = List.of("Cheese", "Meat", "Sausages", "Strong dishes");
        injectWineDishPairing(10L, wineDishPairing);
    }

    private void injectWineRed5() {
        Product product = new Product();
        product.setName("Chardonay");
        product.setPrice(new BigDecimal("62.99"));
        product.setType(ProductType.Type.DRY);
        product.setColor(ProductColor.Color.RED);
        product.setVintage(2020);
        product.setCountry("Italy");
        product.setRegion("Toscana");
        product.setGrape("Xarel·lo, Macabeu, Parellada");
        product.setTaste("Red crianza wine of garnet red hue with hints of russet. First impression is pleasant with a nice aroma. Powerful and complex, has some spices and varietal aromas, rounded because of the oak. In the mouth is warm, good tannin presence but balanced with the acidity. With round body and a long and tasty after taste. Well-balanced, powerful, full body, persistent, and nice tannin balance.");
        product.setTemperature("Serve between 16-18ºC");
        product.setEvent(ProductEvent.Event.FOR_PRESENT);
        product.setPairing("This wine is perfect with cured cheeses, red meats, sausages and strong dishes.");
        product.setImage(ImageReader.getImageBytes("images/products/red5.png"));
        productService.save(product);
        List<String> wineDishPairing = List.of("Cheese", "Meat", "Sausages", "Strong dishes");
        injectWineDishPairing(11L, wineDishPairing);
    }

    private void injectWineRed6() {
        Product product = new Product();
        product.setName("Cabarnet Sauvignon");
        product.setPrice(new BigDecimal("55.99"));
        product.setType(ProductType.Type.SEMISWEET);
        product.setColor(ProductColor.Color.RED);
        product.setVintage(2016);
        product.setCountry("Italy");
        product.setRegion("Toscana");
        product.setGrape("100% Cabernet Sauvignon");
        product.setTaste("Young red wine with an intense red hue flecked with purple. Forms legs when swirled in the glass. Very pleasant first impression, very intense and complex aromas which include notes of jam, ripe red fruit, and liquorice. The aromas on the palate are distinctly varietal in character. The wine round, unctuous, long-lingering, with sweet tannins that make for a long and flavoursome finish. This is a powerful, pleasant wine. It has fruit and a very long-lingering finish, qualities that promise very good development in the bottle.");
        product.setTemperature("Serve between 12-14ºC");
        product.setEvent(ProductEvent.Event.FOR_LONELY_EVENING);
        product.setPairing("Perfect with stewed meat, cured cheese, sausages, and dishes with personality.");
        product.setImage(ImageReader.getImageBytes("images/products/red6.png"));
        productService.save(product);
        List<String> wineDishPairing = List.of("Meat", "Cheese", "Sausages", "Dishes with personality");
        injectWineDishPairing(12L, wineDishPairing);
    }


    private void injectWineWhite1() {
        Product product = new Product();
        product.setName("Dry White");
        product.setPrice(new BigDecimal("55.99"));
        product.setType(ProductType.Type.DRY);
        product.setColor(ProductColor.Color.WHITE);
        product.setVintage(2016);
        product.setCountry("Italy");
        product.setRegion("Toscana");
        product.setGrape("100% Xarel·lo");
        product.setTaste("Pale yellow white wine lightly flecked with green. Forms legs when swirled in the glass. Very pleasant upon first impression, with intense aromas reminiscent of white flowers with hints of ripe fruit. The aromas carry over to the palate, which is distinctly fruity in character, with the Xarel·lo characteristics coming through in the tasting. On the palate, the wine is dry, fresh, and flavoursome, with good acidity. This wine has a significant aromatic potential, with fruit on the palate and a lingering and very pleasant finish, qualities that ensure good development in the bottle.");
        product.setTemperature("Serve between 6-8ºC");
        product.setEvent(ProductEvent.Event.PARTY_WITH_FRIENDS);
        product.setPairing("Perfect with fish, mild cheese, rice and white meats.");
        product.setImage(ImageReader.getImageBytes("images/products/white1.png"));
        productService.save(product);
        List<String> wineDishPairing = List.of("Fish", "Cheese", "Rice", "Meat");
        injectWineDishPairing(13L, wineDishPairing);
    }

    private void injectWineWhite2() {
        Product product = new Product();
        product.setName("Chardonnay Barrel");
        product.setPrice(new BigDecimal("29.99"));
        product.setType(ProductType.Type.DRY);
        product.setColor(ProductColor.Color.WHITE);
        product.setVintage(2017);
        product.setCountry("Italy");
        product.setRegion("Toscana");
        product.setGrape("Xarel·lo, Macabeu, Parellada");
        product.setTaste("White wine with a bright yellow hue flecked with gold. Very pleasant first impression, with subtle vanilla and smoky aromas as well as floral and honey notes. It is warm, creamy, and very pleasant on the palate. Fermentation and ageing of the lees have given this wine personality");
        product.setTemperature("Serve between 10-12ºC");
        product.setEvent(ProductEvent.Event.FOR_LONELY_EVENING);
        product.setPairing("Perfect for all types of aperitifs, fish, rice, smoked dishes, white meat and cheese.");
        product.setImage(ImageReader.getImageBytes("images/products/white2.png"));
        productService.save(product);
        List<String> wineDishPairing = List.of("Fish", "Rice", "Smoked dishes", "Meat", "Cheese");
        injectWineDishPairing(14L, wineDishPairing);
    }

    private void injectWineWhite3() {
        Product product = new Product();
        product.setName("White Semi Sweet");
        product.setPrice(new BigDecimal("85.95"));
        product.setType(ProductType.Type.SEMISWEET);
        product.setColor(ProductColor.Color.WHITE);
        product.setVintage(2015);
        product.setCountry("Italy");
        product.setRegion("Toscana");
        product.setGrape("Xarel·lo, Macabeu, Parellada");
        product.setTaste("Pale yellow white wine with a bright, clear appearance. Powerful and pleasant first impression, with complex, predominant fruity aromas. It is sweet, fresh and round on the palate, with a fruity taste and a lingering bouquet. Aromatic, with a fruity palate, character and a long-lingering finish.");
        product.setTemperature("Serve between 6-8ºC");
        product.setEvent(ProductEvent.Event.FOR_SPECIAL_EVENTS);
        product.setPairing("Combine this wine with risottos, cheese, sausages, poultry, and stewed meats.");
        product.setImage(ImageReader.getImageBytes("images/products/white3.png"));
        productService.save(product);
        List<String> wineDishPairing = List.of("Risottos", "Cheese", "Sausages", "Poultry", "Meat");
        injectWineDishPairing(15L, wineDishPairing);
    }

    private void injectWineWhite4() {
        Product product = new Product();
        product.setName("Ice");
        product.setPrice(new BigDecimal("29.99"));
        product.setType(ProductType.Type.DRY);
        product.setColor(ProductColor.Color.WHITE);
        product.setVintage(2018);
        product.setCountry("Italy");
        product.setRegion("Toscana");
        product.setGrape("100% Cabernet Sauvignon");
        product.setTaste("Pale yellow white wine with a bright, clear appearance. Powerful and pleasant first impression, with complex, predominant fruity aromas. It is sweet, fresh and round on the palate, with a fruity taste and a lingering bouquet. Aromatic, with a fruity palate, character and a long-lingering finish.");
        product.setTemperature("Serve between 6-8ºC");
        product.setEvent(ProductEvent.Event.PARTY_WITH_FRIENDS);
        product.setPairing("This wine is perfect with cured cheeses, red meats, sausages and strong dishes.");
        product.setImage(ImageReader.getImageBytes("images/products/white4.png"));
        productService.save(product);
        List<String> wineDishPairing = List.of("Cheese", "Meat", "Sausages", "Strong dishes");
        injectWineDishPairing(16L, wineDishPairing);
    }

    private void injectWineWhite5() {
        Product product = new Product();
        product.setName("Chardonay");
        product.setPrice(new BigDecimal("62.99"));
        product.setType(ProductType.Type.DRY);
        product.setColor(ProductColor.Color.WHITE);
        product.setVintage(2020);
        product.setCountry("Italy");
        product.setRegion("Toscana");
        product.setGrape("Chardonnay, Xarel·lo, Macabeu");
        product.setTaste("Pale yellow flecked with gold, with a steady stream of small bubbles forming beads. First impression: good, powerful and refined; aromas from bottle-ageing, as well as ripe fruit and flowers, can be detected. It is round, pleasant, long-lingering and fresh on the palate. Well-balanced, harmonious, direct, dry, with a long-lingering and pleasant finish.");
        product.setTemperature("Serve between 6-8ºC");
        product.setEvent(ProductEvent.Event.FOR_SPECIAL_EVENTS);
        product.setPairing("Perfect for salads, vegetables, fishes and mild cheeses. It can also be taken as an aperitif.");
        product.setImage(ImageReader.getImageBytes("images/products/white5.png"));
        productService.save(product);
        List<String> wineDishPairing = List.of("Salads", "Vegetables", "Fish", "Cheese");
        injectWineDishPairing(17L, wineDishPairing);
    }

    private void injectWineWhite6() {
        Product product = new Product();
        product.setName("Reserva Especial");
        product.setPrice(new BigDecimal("55.99"));
        product.setType(ProductType.Type.SEMISWEET);
        product.setColor(ProductColor.Color.WHITE);
        product.setVintage(2016);
        product.setCountry("Italy");
        product.setRegion("Toscana");
        product.setGrape("Chardonnay, Xarel·lo, Macabeu");
        product.setTaste("Pale yellow white wine lightly flecked with green. Forms legs when swirled in the glass. Very pleasant upon first impression, with intense aromas reminiscent of white flowers with hints of ripe fruit. The aromas carry over to the palate, which is distinctly fruity in character, with the Xarel·lo characteristics coming through in the tasting. On the palate, the wine is dry, fresh, and flavoursome, with good acidity. This wine has a significant aromatic potential, with fruit on the palate and a lingering and very pleasant finish, qualities that ensure good development in the bottle.");
        product.setTemperature("Serve between 6-8ºC");
        product.setEvent(ProductEvent.Event.FOR_PRESENT);
        product.setPairing("The great personality: stewed meats and fish, cheese, foie, and smoked foods");
        product.setImage(ImageReader.getImageBytes("images/products/white6.png"));
        productService.save(product);
        List<String> wineDishPairing = List.of("Meat", "Fish", "Cheese", "Foie", "Smoked dishes");
        injectWineDishPairing(18L, wineDishPairing);
    }

    private void injectWineDishPairing(Long productId, List<String> pairingNames) {
        for (String pairingName : pairingNames) {
            WineDishPairing wineDishPairing = new WineDishPairing();
            wineDishPairing.setWine(productService.getById(productId));
            wineDishPairing.setDish(dishService.getByName(pairingName));
            wineDishPairingService.save(wineDishPairing);
        }
    }

}
