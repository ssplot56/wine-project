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
import java.util.Map;
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

    //http://localhost:8080/wine-shop/products?size=6&page=0&sortBy=price
    //http://localhost:8080/wine-shop/products?sortBy=price:DESC;name:ASC&page=0
    @GetMapping("/pagedsorted")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(@RequestParam (defaultValue = "20") Integer size,
                                                                   @RequestParam (defaultValue = "0") Integer page,
                                                                   @RequestParam (defaultValue = "id") String sortBy) {
        PageRequest pageRequest = UrlParser.formPageRequest(page, size, sortBy);
        List<ProductResponseDto> responseDtos = productService.findAll(pageRequest)
                .stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    //http://localhost:8080/wine-shop/products/filtered?type=DRY&event=FOR_SPECIAL_EVENTS
    //http://localhost:8080/wine-shop/products/filtered?dish=Cheese,Meet
    @GetMapping("/filtered")
    public ResponseEntity<List<ProductResponseDto>> getFilteredProducts(@RequestParam Map<String, String> params) {
        List<ProductResponseDto> responseDtos = productService.findAll(params)
                .stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProducts(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String event,
            @RequestParam(required = false) String dishes,
            @RequestParam(defaultValue = "id:ASC") String sortBy,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        List<ProductResponseDto> responseDtos = productService.getProducts(
                type, color, event, dishes, sortBy, page, size)
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

    private void injectWinePink1() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Sauvignon Rosé");
        requestDto.setPrice(new BigDecimal("42.99"));
        requestDto.setType(ProductType.Type.SEMISWEET.name());
        requestDto.setColor(ProductColor.Color.PINK.name());
        requestDto.setEvent(ProductEvent.Event.FOR_PRESENT.name());
        requestDto.setPairing("Perfect with pasta, tender meat, cheese, seafood, rice and also as an aperitif.");
        requestDto.setVintage(2020);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("100% Cabernet Sauvignon");
        requestDto.setTaste("Bright cherry-coloured rosé wine. Forms legs when swirled in the glass. Intense and very pleasant first impression, with the fruity aromas that are typical of the Cabernet Sauvignon variety. The aromas on the palate are intense. It is very round, fresh and elegant, with notes of strawberry and caramel. The finish is very long and complex. This is a very aromatic wine, with a complex palate and a long aromatic finish, qualities that promise very good development in the bottle.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setImageLink("images/products/pink1.png");
        List<String> wineDishPairing = List.of("Pasta", "Meat", "Cheese", "Seafood", "Rice");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
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
        requestDto.setTaste("Red crianza wine of garnet red hue with hints of russet. First impression is pleasant and refined. Varietal aromas and aromas from barrel ageing can be detected. It is powerful, pleasant and direct on the palate, with notes of vanilla and oak that do not mask the characteristics of the grape varieties from which it is made. Good intensity and finish.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setEvent(ProductEvent.Event.PARTY_WITH_FRIENDS.name());
        requestDto.setPairing("Perfect as an aperitif and to accompany rice, fish, seafood and braised white and red meat.");
        requestDto.setImageLink("images/products/pink2.png");
        List<String> wineDishPairing = List.of("Rice", "Fish", "Seafood", "Meat");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
    }

    private void injectWinePink3() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Chardonay Rosé");
        requestDto.setPrice(new BigDecimal("25.99"));
        requestDto.setType(ProductType.Type.SEMISWEET.name());
        requestDto.setColor(ProductColor.Color.PINK.name());
        requestDto.setVintage(2020);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("Xarel·lo, Macabeu, Parellada");
        requestDto.setTaste("Pale yellow with green reflections has a splendid effervescencia with rosaries of very fine bubbles that form a crown. The nose is fresh, with notes of peach and apricot. In the mouth it is round, very pleasant with a finish reminiscent of ripe fruit and pastry.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_SPECIAL_EVENTS.name());
        requestDto.setPairing("Perfect with pasta, tender meat, cheese, seafood, rice and also as an aperitif.");
        requestDto.setImageLink("images/products/pink3.png");
        List<String> wineDishPairing = List.of("Pasta", "Meat", "Cheese", "Seafood", "Rice");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
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
        requestDto.setTaste("Cherry-red rosé wine with crystalline aspect. Nice first impression of fine fruity aromas. It's nice on the palate, fresh, good, balanced, round, and with a good acidity. The after taste is elegant and fruity. It is an harmonic Rosé wine , correct and with a medium persistence.");
        requestDto.setTemperature("Serve between 16-18ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_SPECIAL_EVENTS.name());
        requestDto.setPairing("Perfect with salads, chicken, rise, tender meats and barbeques.");
        requestDto.setImageLink("images/products/pink4.png");
        List<String> wineDishPairing = List.of("Salads", "Chicken", "Rice", "Meat", "Barbeques");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
    }

    private void injectWinePink5() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Chardonay Rosé");
        requestDto.setPrice(new BigDecimal("57.99"));
        requestDto.setType(ProductType.Type.SEMISWEET.name());
        requestDto.setColor(ProductColor.Color.PINK.name());
        requestDto.setVintage(2020);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("Xarel·lo, Macabeu, Parellada");
        requestDto.setTaste("Pale yellow with green reflections has a splendid effervescencia with rosaries of very fine bubbles that form a crown. The nose is fresh, with notes of peach and apricot. In the mouth it is round, very pleasant with a finish reminiscent of ripe fruit and pastry.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_LONELY_EVENING.name());
        requestDto.setPairing("Perfect with pasta, tender meat, cheese, seafood, rice and also as an aperitif.");
        requestDto.setImageLink("images/products/pink5.png");
        List<String> wineDishPairing = List.of("Pasta", "Meat", "Cheese", "Seafood", "Rice");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
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
        requestDto.setTaste("Lively and bright, intense pink hue. Good release of small bubbles that rise to form a distinct crown. First impression: pleasant and powerful, followed by aromas reminiscent of red berries. It is clean and sweet on the palate, with a good, long-lasting finish. Balanced, full of flavour, and very fruity.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_PRESENT.name());
        requestDto.setPairing("Perfect as an aperitif or dessert. It can, also, accompany mild cheeses and rices.");
        requestDto.setImageLink("images/products/pink6.png");
        List<String> wineDishPairing = List.of("Cheese", "Rice");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
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
        requestDto.setTaste("Young red wine with an intense red hue flecked with purple. Forms legs when swirled in the glass. Very pleasant first impression, very intense and complex aromas which include notes of jam, ripe red fruit, and liquorice. The aromas on the palate are distinctly varietal in character. The wine round, unctuous, long-lingering, with sweet tannins that make for a long and flavoursome finish. This is a powerful, pleasant wine. It has fruit and a very long-lingering finish, qualities that promise very good development in the bottle.");
        requestDto.setTemperature("Serve between 12-14ºC");
        requestDto.setEvent(ProductEvent.Event.PARTY_WITH_FRIENDS.name());
        requestDto.setPairing("Perfect with stewed meat, cured cheese, sausages, and dishes with personality.");
        requestDto.setImageLink("images/products/red1.png");
        List<String> wineDishPairing = List.of("Meat", "Cheese", "Sausages");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
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
        requestDto.setTaste("Young red wine with an intense red hue flecked with purple. Forms legs when swirled in the glass. Very pleasant first impression, very intense and complex aromas which include notes of jam, ripe red fruit, and liquorice. The aromas on the palate are distinctly varietal in character. The wine round, unctuous, long-lingering, with sweet tannins that make for a long and flavoursome finish. This is a powerful, pleasant wine. It has fruit and a very long-lingering finish, qualities that promise very good development in the bottle.");
        requestDto.setTemperature("Serve between 12-14ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_PRESENT.name());
        requestDto.setPairing("Combine this wine with risottos, cheese, sausages, poultry, and stewed meats.");
        requestDto.setImageLink("images/products/red2.png");
        List<String> wineDishPairing = List.of("Risottos", "Cheese", "Sausages", "Poultry", "Meat");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
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
        requestDto.setTaste("Red crianza wine of garnet red hue with hints of russet. First impression is pleasant and refined. Varietal aromas and aromas from barrel ageing can be detected. It is powerful, pleasant and direct on the palate, with notes of vanilla and oak that do not mask the characteristics of the grape varieties from which it is made. Good intensity and finish.");
        requestDto.setTemperature("Serve between 16-18ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_SPECIAL_EVENTS.name());
        requestDto.setPairing("Combine this wine with risottos, cheese, sausages, poultry, and stewed meats.");
        requestDto.setImageLink("images/products/red3.png");
        List<String> wineDishPairing = List.of("Risottos", "Cheese", "Sausages", "Poultry", "Meat");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
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
        requestDto.setTaste("Red crianza wine of garnet red hue with hints of russet. First impression is pleasant with a nice aroma. Powerful and complex, has some spices and varietal aromas, rounded because of the oak. In the mouth is warm, good tannin presence but balanced with the acidity. With round body and a long and tasty after taste. Well-balanced, powerful, full body, persistent, and nice tannin balance.");
        requestDto.setTemperature("Serve between 16-18ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_SPECIAL_EVENTS.name());
        requestDto.setPairing("This wine is perfect with cured cheeses, red meats, sausages and strong dishes.");
        requestDto.setImageLink("images/products/red4.png");
        List<String> wineDishPairing = List.of("Cheese", "Meat", "Sausages", "Strong dishes");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
    }

    private void injectWineRed5() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Chardonay");
        requestDto.setPrice(new BigDecimal("62.99"));
        requestDto.setType(ProductType.Type.DRY.name());
        requestDto.setColor(ProductColor.Color.RED.name());
        requestDto.setVintage(2020);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("Xarel·lo, Macabeu, Parellada");
        requestDto.setTaste("Red crianza wine of garnet red hue with hints of russet. First impression is pleasant with a nice aroma. Powerful and complex, has some spices and varietal aromas, rounded because of the oak. In the mouth is warm, good tannin presence but balanced with the acidity. With round body and a long and tasty after taste. Well-balanced, powerful, full body, persistent, and nice tannin balance.");
        requestDto.setTemperature("Serve between 16-18ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_PRESENT.name());
        requestDto.setPairing("This wine is perfect with cured cheeses, red meats, sausages and strong dishes.");
        requestDto.setImageLink("images/products/red5.png");
        List<String> wineDishPairing = List.of("Cheese", "Meat", "Sausages", "Strong dishes");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
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
        requestDto.setTaste("Young red wine with an intense red hue flecked with purple. Forms legs when swirled in the glass. Very pleasant first impression, very intense and complex aromas which include notes of jam, ripe red fruit, and liquorice. The aromas on the palate are distinctly varietal in character. The wine round, unctuous, long-lingering, with sweet tannins that make for a long and flavoursome finish. This is a powerful, pleasant wine. It has fruit and a very long-lingering finish, qualities that promise very good development in the bottle.");
        requestDto.setTemperature("Serve between 12-14ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_LONELY_EVENING.name());
        requestDto.setPairing("Perfect with stewed meat, cured cheese, sausages, and dishes with personality.");
        requestDto.setImageLink("images/products/red6.png");
        List<String> wineDishPairing = List.of("Meat", "Cheese", "Sausages", "Dishes with personality");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
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
        requestDto.setTaste("Pale yellow white wine lightly flecked with green. Forms legs when swirled in the glass. Very pleasant upon first impression, with intense aromas reminiscent of white flowers with hints of ripe fruit. The aromas carry over to the palate, which is distinctly fruity in character, with the Xarel·lo characteristics coming through in the tasting. On the palate, the wine is dry, fresh, and flavoursome, with good acidity. This wine has a significant aromatic potential, with fruit on the palate and a lingering and very pleasant finish, qualities that ensure good development in the bottle.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setEvent(ProductEvent.Event.PARTY_WITH_FRIENDS.name());
        requestDto.setPairing("Perfect with fish, mild cheese, rice and white meats.");
        requestDto.setImageLink("images/products/white1.png");
        List<String> wineDishPairing = List.of("Fish", "Cheese", "Rice", "Meat");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
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
        requestDto.setTaste("White wine with a bright yellow hue flecked with gold. Very pleasant first impression, with subtle vanilla and smoky aromas as well as floral and honey notes. It is warm, creamy, and very pleasant on the palate. Fermentation and ageing of the lees have given this wine personality");
        requestDto.setTemperature("Serve between 10-12ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_LONELY_EVENING.name());
        requestDto.setPairing("Perfect for all types of aperitifs, fish, rice, smoked dishes, white meat and cheese.");
        requestDto.setImageLink("images/products/white2.png");
        List<String> wineDishPairing = List.of("Fish", "Rice", "Smoked dishes", "Meat", "Cheese");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
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
        requestDto.setTaste("Pale yellow white wine with a bright, clear appearance. Powerful and pleasant first impression, with complex, predominant fruity aromas. It is sweet, fresh and round on the palate, with a fruity taste and a lingering bouquet. Aromatic, with a fruity palate, character and a long-lingering finish.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_SPECIAL_EVENTS.name());
        requestDto.setPairing("Combine this wine with risottos, cheese, sausages, poultry, and stewed meats.");
        requestDto.setImageLink("images/products/white3.png");
        List<String> wineDishPairing = List.of("Risottos", "Cheese", "Sausages", "Poultry", "Meat");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
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
        requestDto.setTaste("Pale yellow white wine with a bright, clear appearance. Powerful and pleasant first impression, with complex, predominant fruity aromas. It is sweet, fresh and round on the palate, with a fruity taste and a lingering bouquet. Aromatic, with a fruity palate, character and a long-lingering finish.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setEvent(ProductEvent.Event.PARTY_WITH_FRIENDS.name());
        requestDto.setPairing("This wine is perfect with cured cheeses, red meats, sausages and strong dishes.");
        requestDto.setImageLink("images/products/white4.png");
        List<String> wineDishPairing = List.of("Cheese", "Meat", "Sausages", "Strong dishes");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
    }

    private void injectWineWhite5() {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName("Chardonay");
        requestDto.setPrice(new BigDecimal("62.99"));
        requestDto.setType(ProductType.Type.DRY.name());
        requestDto.setColor(ProductColor.Color.WHITE.name());
        requestDto.setVintage(2020);
        requestDto.setCountry("Italy");
        requestDto.setRegion("Toscana");
        requestDto.setGrape("Chardonnay, Xarel·lo, Macabeu");
        requestDto.setTaste("Pale yellow flecked with gold, with a steady stream of small bubbles forming beads. First impression: good, powerful and refined; aromas from bottle-ageing, as well as ripe fruit and flowers, can be detected. It is round, pleasant, long-lingering and fresh on the palate. Well-balanced, harmonious, direct, dry, with a long-lingering and pleasant finish.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_SPECIAL_EVENTS.name());
        requestDto.setPairing("Perfect for salads, vegetables, fishes and mild cheeses. It can also be taken as an aperitif.");
        requestDto.setImageLink("images/products/white5.png");
        List<String> wineDishPairing = List.of("Salads", "Vegetables", "Fish", "Cheese");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
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
        requestDto.setTaste("Pale yellow white wine lightly flecked with green. Forms legs when swirled in the glass. Very pleasant upon first impression, with intense aromas reminiscent of white flowers with hints of ripe fruit. The aromas carry over to the palate, which is distinctly fruity in character, with the Xarel·lo characteristics coming through in the tasting. On the palate, the wine is dry, fresh, and flavoursome, with good acidity. This wine has a significant aromatic potential, with fruit on the palate and a lingering and very pleasant finish, qualities that ensure good development in the bottle.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_PRESENT.name());
        requestDto.setPairing("The great personality: stewed meats and fish, cheese, foie, and smoked foods");
        requestDto.setImageLink("images/products/white6.png");
        List<String> wineDishPairing = List.of("Meat", "Fish", "Cheese", "Foie", "Smoked dishes");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
    }

}
