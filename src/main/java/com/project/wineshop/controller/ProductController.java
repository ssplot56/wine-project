package com.project.wineshop.controller;

import com.project.wineshop.dto.request.ProductRequestDto;
import com.project.wineshop.dto.response.ProductResponseDto;
import com.project.wineshop.model.Product;
import com.project.wineshop.model.enums.ProductColor;
import com.project.wineshop.model.enums.ProductEvent;
import com.project.wineshop.model.enums.ProductType;
import com.project.wineshop.service.ProductService;
import com.project.wineshop.service.mapper.impl.ProductMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody @Valid ProductRequestDto requestDto) {
        Product product = productMapper.mapToModel(requestDto);
        Product savedProduct = productService.save(product);
        return ResponseEntity.ok(productMapper.mapToDto(savedProduct));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProducts(@RequestParam Map<String, String> params) {
        List<ProductResponseDto> responseDtos = productService.findAll(params)
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
        requestDto.setImageLink("https://wine-shop-images.s3.eu-north-1.amazonaws.com/products/pink1.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJD%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJGMEQCIDinurI70tcQK0d7dt8dijV%2FZwTBZpsgVJb%2Fdjda%2FcbyAiAXDhGizXD7j%2BwOChu7LBmddZnNRTM7qSXqvitX9YjuSCrtAgiJ%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDQ3MDU1MTYwMjc5MiIMq2m%2FmrJrx5oJUDchKsECbR%2BsX5HmqaphKxymdvhLNdgv8%2B8JK1pDhAhyOVEcOe1p0rdfIUduIRbxMj8y7oqQB5uo%2BzPDDs4t55dMzFFOHVmdO4XCQ08PWUrYZvN5%2FKhLJ5Hy5WUVLhMwbzb3sawek1uJJfxg7rJGlURufl%2FglN1CYpiNElLAGemAQ5pXZmdMdQb9yvgyBsVHqepIHrDlfG0HNCxJ6vXhgBEEKtvvTvo%2BKQ7TqJ06IaLa%2FXq2FdOcmmKHxGId54zkq4zL8OYeJEm%2F%2FI0YriVuPO19D1b8yxqtCVqLaDv8lqfMdvLQZ5m%2FzAtWtLRcFfRFwbfqe4hCqylhZ3Z4H4RduNbc5yg1xQVrn1ccSO2obaDpEkREz24bT3oFo1O2RCKkoKMatjMRKSg8zduChgVwdntFlL9gbBL5651S0CeUMdHbWNjvZOyxMNbTg6IGOrQCF503WO7%2FjkiYjMh05xGa99yqLPgPNzG2NYa2n549%2BOhysw84LbR55zBkBNR1MBjKoPtD24PDkNDsp%2BzvOnjGnsN9%2FOeUIA1GFjA0s6s%2B95yHR8w6ifzRJTV4cbtujeCd1n8UCJuc7Ac%2FvpEL%2B2dHISQas6dbbXiaAA%2F1bAP29MGPSfr%2F5l4KGpE%2FVXG3XGvXvEnf2k%2B4XifVIY50GQJZb702YStJ8dcD9AIPxek5LruYYdVdjInO%2BuDmJyGhC5HKNDizeRi4FykbuRHPY%2B2VnVm0lyTbw0r%2BeV6otPgaiiTOdtYhoRbdcOBRwv2DqYjFsnhGmRxAoXwCaWc0%2BJQGjx3O8L6uwyWnJ%2F1QDmKzBcXm8p5UxKGhmx9MC44TPWvT3AkNlPCOvvzhrOfdEBYaFSKWR%2Bc%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230420T075140Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43200&X-Amz-Credential=ASIAW3DYPUZUF6FV4DHS%2F20230420%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=f9a375fe9e88ad52f2051c3aeff5079ebb8e37d9fcbd0b3b421d08b840e4905b");
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
        requestDto.setImageLink("https://wine-shop-images.s3.eu-north-1.amazonaws.com/products/pink2.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJD%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJGMEQCIDinurI70tcQK0d7dt8dijV%2FZwTBZpsgVJb%2Fdjda%2FcbyAiAXDhGizXD7j%2BwOChu7LBmddZnNRTM7qSXqvitX9YjuSCrtAgiJ%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDQ3MDU1MTYwMjc5MiIMq2m%2FmrJrx5oJUDchKsECbR%2BsX5HmqaphKxymdvhLNdgv8%2B8JK1pDhAhyOVEcOe1p0rdfIUduIRbxMj8y7oqQB5uo%2BzPDDs4t55dMzFFOHVmdO4XCQ08PWUrYZvN5%2FKhLJ5Hy5WUVLhMwbzb3sawek1uJJfxg7rJGlURufl%2FglN1CYpiNElLAGemAQ5pXZmdMdQb9yvgyBsVHqepIHrDlfG0HNCxJ6vXhgBEEKtvvTvo%2BKQ7TqJ06IaLa%2FXq2FdOcmmKHxGId54zkq4zL8OYeJEm%2F%2FI0YriVuPO19D1b8yxqtCVqLaDv8lqfMdvLQZ5m%2FzAtWtLRcFfRFwbfqe4hCqylhZ3Z4H4RduNbc5yg1xQVrn1ccSO2obaDpEkREz24bT3oFo1O2RCKkoKMatjMRKSg8zduChgVwdntFlL9gbBL5651S0CeUMdHbWNjvZOyxMNbTg6IGOrQCF503WO7%2FjkiYjMh05xGa99yqLPgPNzG2NYa2n549%2BOhysw84LbR55zBkBNR1MBjKoPtD24PDkNDsp%2BzvOnjGnsN9%2FOeUIA1GFjA0s6s%2B95yHR8w6ifzRJTV4cbtujeCd1n8UCJuc7Ac%2FvpEL%2B2dHISQas6dbbXiaAA%2F1bAP29MGPSfr%2F5l4KGpE%2FVXG3XGvXvEnf2k%2B4XifVIY50GQJZb702YStJ8dcD9AIPxek5LruYYdVdjInO%2BuDmJyGhC5HKNDizeRi4FykbuRHPY%2B2VnVm0lyTbw0r%2BeV6otPgaiiTOdtYhoRbdcOBRwv2DqYjFsnhGmRxAoXwCaWc0%2BJQGjx3O8L6uwyWnJ%2F1QDmKzBcXm8p5UxKGhmx9MC44TPWvT3AkNlPCOvvzhrOfdEBYaFSKWR%2Bc%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230420T080304Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43200&X-Amz-Credential=ASIAW3DYPUZUF6FV4DHS%2F20230420%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=2537e6f079ab2cc48b501c75bb82b3fd35454193ee3a8a92a33bf4b3f8fd181e");
        List<String> wineDishPairing = List.of("Rice", "Fish", "Seafood", "Meat");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
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
        requestDto.setTaste("Pale yellow with green reflections has a splendid effervescencia with rosaries of very fine bubbles that form a crown. The nose is fresh, with notes of peach and apricot. In the mouth it is round, very pleasant with a finish reminiscent of ripe fruit and pastry.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_SPECIAL_EVENTS.name());
        requestDto.setPairing("Perfect with pasta, tender meat, cheese, seafood, rice and also as an aperitif.");
        requestDto.setImageLink("https://wine-shop-images.s3.eu-north-1.amazonaws.com/products/pink3.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJD%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJGMEQCIDinurI70tcQK0d7dt8dijV%2FZwTBZpsgVJb%2Fdjda%2FcbyAiAXDhGizXD7j%2BwOChu7LBmddZnNRTM7qSXqvitX9YjuSCrtAgiJ%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDQ3MDU1MTYwMjc5MiIMq2m%2FmrJrx5oJUDchKsECbR%2BsX5HmqaphKxymdvhLNdgv8%2B8JK1pDhAhyOVEcOe1p0rdfIUduIRbxMj8y7oqQB5uo%2BzPDDs4t55dMzFFOHVmdO4XCQ08PWUrYZvN5%2FKhLJ5Hy5WUVLhMwbzb3sawek1uJJfxg7rJGlURufl%2FglN1CYpiNElLAGemAQ5pXZmdMdQb9yvgyBsVHqepIHrDlfG0HNCxJ6vXhgBEEKtvvTvo%2BKQ7TqJ06IaLa%2FXq2FdOcmmKHxGId54zkq4zL8OYeJEm%2F%2FI0YriVuPO19D1b8yxqtCVqLaDv8lqfMdvLQZ5m%2FzAtWtLRcFfRFwbfqe4hCqylhZ3Z4H4RduNbc5yg1xQVrn1ccSO2obaDpEkREz24bT3oFo1O2RCKkoKMatjMRKSg8zduChgVwdntFlL9gbBL5651S0CeUMdHbWNjvZOyxMNbTg6IGOrQCF503WO7%2FjkiYjMh05xGa99yqLPgPNzG2NYa2n549%2BOhysw84LbR55zBkBNR1MBjKoPtD24PDkNDsp%2BzvOnjGnsN9%2FOeUIA1GFjA0s6s%2B95yHR8w6ifzRJTV4cbtujeCd1n8UCJuc7Ac%2FvpEL%2B2dHISQas6dbbXiaAA%2F1bAP29MGPSfr%2F5l4KGpE%2FVXG3XGvXvEnf2k%2B4XifVIY50GQJZb702YStJ8dcD9AIPxek5LruYYdVdjInO%2BuDmJyGhC5HKNDizeRi4FykbuRHPY%2B2VnVm0lyTbw0r%2BeV6otPgaiiTOdtYhoRbdcOBRwv2DqYjFsnhGmRxAoXwCaWc0%2BJQGjx3O8L6uwyWnJ%2F1QDmKzBcXm8p5UxKGhmx9MC44TPWvT3AkNlPCOvvzhrOfdEBYaFSKWR%2Bc%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230420T080402Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43200&X-Amz-Credential=ASIAW3DYPUZUF6FV4DHS%2F20230420%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=af66b37636186e94363ce508030c44703a2c431d909d54897bcecc547e19e4e7");
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
        requestDto.setImageLink("https://wine-shop-images.s3.eu-north-1.amazonaws.com/products/pink4.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJD%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJGMEQCIDinurI70tcQK0d7dt8dijV%2FZwTBZpsgVJb%2Fdjda%2FcbyAiAXDhGizXD7j%2BwOChu7LBmddZnNRTM7qSXqvitX9YjuSCrtAgiJ%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDQ3MDU1MTYwMjc5MiIMq2m%2FmrJrx5oJUDchKsECbR%2BsX5HmqaphKxymdvhLNdgv8%2B8JK1pDhAhyOVEcOe1p0rdfIUduIRbxMj8y7oqQB5uo%2BzPDDs4t55dMzFFOHVmdO4XCQ08PWUrYZvN5%2FKhLJ5Hy5WUVLhMwbzb3sawek1uJJfxg7rJGlURufl%2FglN1CYpiNElLAGemAQ5pXZmdMdQb9yvgyBsVHqepIHrDlfG0HNCxJ6vXhgBEEKtvvTvo%2BKQ7TqJ06IaLa%2FXq2FdOcmmKHxGId54zkq4zL8OYeJEm%2F%2FI0YriVuPO19D1b8yxqtCVqLaDv8lqfMdvLQZ5m%2FzAtWtLRcFfRFwbfqe4hCqylhZ3Z4H4RduNbc5yg1xQVrn1ccSO2obaDpEkREz24bT3oFo1O2RCKkoKMatjMRKSg8zduChgVwdntFlL9gbBL5651S0CeUMdHbWNjvZOyxMNbTg6IGOrQCF503WO7%2FjkiYjMh05xGa99yqLPgPNzG2NYa2n549%2BOhysw84LbR55zBkBNR1MBjKoPtD24PDkNDsp%2BzvOnjGnsN9%2FOeUIA1GFjA0s6s%2B95yHR8w6ifzRJTV4cbtujeCd1n8UCJuc7Ac%2FvpEL%2B2dHISQas6dbbXiaAA%2F1bAP29MGPSfr%2F5l4KGpE%2FVXG3XGvXvEnf2k%2B4XifVIY50GQJZb702YStJ8dcD9AIPxek5LruYYdVdjInO%2BuDmJyGhC5HKNDizeRi4FykbuRHPY%2B2VnVm0lyTbw0r%2BeV6otPgaiiTOdtYhoRbdcOBRwv2DqYjFsnhGmRxAoXwCaWc0%2BJQGjx3O8L6uwyWnJ%2F1QDmKzBcXm8p5UxKGhmx9MC44TPWvT3AkNlPCOvvzhrOfdEBYaFSKWR%2Bc%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230420T080501Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43200&X-Amz-Credential=ASIAW3DYPUZUF6FV4DHS%2F20230420%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=3c447a1830bc46e02262e329c13cb31067b4612788489c3092daa288fa771c59");
        List<String> wineDishPairing = List.of("Salads", "Chicken", "Rice", "Meat", "Barbeques");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
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
        requestDto.setTaste("Pale yellow with green reflections has a splendid effervescencia with rosaries of very fine bubbles that form a crown. The nose is fresh, with notes of peach and apricot. In the mouth it is round, very pleasant with a finish reminiscent of ripe fruit and pastry.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_LONELY_EVENING.name());
        requestDto.setPairing("Perfect with pasta, tender meat, cheese, seafood, rice and also as an aperitif.");
        requestDto.setImageLink("https://wine-shop-images.s3.eu-north-1.amazonaws.com/products/pink5.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJD%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJGMEQCIDinurI70tcQK0d7dt8dijV%2FZwTBZpsgVJb%2Fdjda%2FcbyAiAXDhGizXD7j%2BwOChu7LBmddZnNRTM7qSXqvitX9YjuSCrtAgiJ%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDQ3MDU1MTYwMjc5MiIMq2m%2FmrJrx5oJUDchKsECbR%2BsX5HmqaphKxymdvhLNdgv8%2B8JK1pDhAhyOVEcOe1p0rdfIUduIRbxMj8y7oqQB5uo%2BzPDDs4t55dMzFFOHVmdO4XCQ08PWUrYZvN5%2FKhLJ5Hy5WUVLhMwbzb3sawek1uJJfxg7rJGlURufl%2FglN1CYpiNElLAGemAQ5pXZmdMdQb9yvgyBsVHqepIHrDlfG0HNCxJ6vXhgBEEKtvvTvo%2BKQ7TqJ06IaLa%2FXq2FdOcmmKHxGId54zkq4zL8OYeJEm%2F%2FI0YriVuPO19D1b8yxqtCVqLaDv8lqfMdvLQZ5m%2FzAtWtLRcFfRFwbfqe4hCqylhZ3Z4H4RduNbc5yg1xQVrn1ccSO2obaDpEkREz24bT3oFo1O2RCKkoKMatjMRKSg8zduChgVwdntFlL9gbBL5651S0CeUMdHbWNjvZOyxMNbTg6IGOrQCF503WO7%2FjkiYjMh05xGa99yqLPgPNzG2NYa2n549%2BOhysw84LbR55zBkBNR1MBjKoPtD24PDkNDsp%2BzvOnjGnsN9%2FOeUIA1GFjA0s6s%2B95yHR8w6ifzRJTV4cbtujeCd1n8UCJuc7Ac%2FvpEL%2B2dHISQas6dbbXiaAA%2F1bAP29MGPSfr%2F5l4KGpE%2FVXG3XGvXvEnf2k%2B4XifVIY50GQJZb702YStJ8dcD9AIPxek5LruYYdVdjInO%2BuDmJyGhC5HKNDizeRi4FykbuRHPY%2B2VnVm0lyTbw0r%2BeV6otPgaiiTOdtYhoRbdcOBRwv2DqYjFsnhGmRxAoXwCaWc0%2BJQGjx3O8L6uwyWnJ%2F1QDmKzBcXm8p5UxKGhmx9MC44TPWvT3AkNlPCOvvzhrOfdEBYaFSKWR%2Bc%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230420T080529Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43200&X-Amz-Credential=ASIAW3DYPUZUF6FV4DHS%2F20230420%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=7486313c335e36ede1f3ec374479f43650f4a586c72869d32758c78d5dc7d2c8");
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
        requestDto.setImageLink("https://wine-shop-images.s3.eu-north-1.amazonaws.com/products/pink6.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJD%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJGMEQCIDinurI70tcQK0d7dt8dijV%2FZwTBZpsgVJb%2Fdjda%2FcbyAiAXDhGizXD7j%2BwOChu7LBmddZnNRTM7qSXqvitX9YjuSCrtAgiJ%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDQ3MDU1MTYwMjc5MiIMq2m%2FmrJrx5oJUDchKsECbR%2BsX5HmqaphKxymdvhLNdgv8%2B8JK1pDhAhyOVEcOe1p0rdfIUduIRbxMj8y7oqQB5uo%2BzPDDs4t55dMzFFOHVmdO4XCQ08PWUrYZvN5%2FKhLJ5Hy5WUVLhMwbzb3sawek1uJJfxg7rJGlURufl%2FglN1CYpiNElLAGemAQ5pXZmdMdQb9yvgyBsVHqepIHrDlfG0HNCxJ6vXhgBEEKtvvTvo%2BKQ7TqJ06IaLa%2FXq2FdOcmmKHxGId54zkq4zL8OYeJEm%2F%2FI0YriVuPO19D1b8yxqtCVqLaDv8lqfMdvLQZ5m%2FzAtWtLRcFfRFwbfqe4hCqylhZ3Z4H4RduNbc5yg1xQVrn1ccSO2obaDpEkREz24bT3oFo1O2RCKkoKMatjMRKSg8zduChgVwdntFlL9gbBL5651S0CeUMdHbWNjvZOyxMNbTg6IGOrQCF503WO7%2FjkiYjMh05xGa99yqLPgPNzG2NYa2n549%2BOhysw84LbR55zBkBNR1MBjKoPtD24PDkNDsp%2BzvOnjGnsN9%2FOeUIA1GFjA0s6s%2B95yHR8w6ifzRJTV4cbtujeCd1n8UCJuc7Ac%2FvpEL%2B2dHISQas6dbbXiaAA%2F1bAP29MGPSfr%2F5l4KGpE%2FVXG3XGvXvEnf2k%2B4XifVIY50GQJZb702YStJ8dcD9AIPxek5LruYYdVdjInO%2BuDmJyGhC5HKNDizeRi4FykbuRHPY%2B2VnVm0lyTbw0r%2BeV6otPgaiiTOdtYhoRbdcOBRwv2DqYjFsnhGmRxAoXwCaWc0%2BJQGjx3O8L6uwyWnJ%2F1QDmKzBcXm8p5UxKGhmx9MC44TPWvT3AkNlPCOvvzhrOfdEBYaFSKWR%2Bc%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230420T080609Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43200&X-Amz-Credential=ASIAW3DYPUZUF6FV4DHS%2F20230420%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=6b3de78fb26d9788271c7497883b2a08c507a0453ad3023b9062235a4efe6cd5");
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
        requestDto.setImageLink("https://wine-shop-images.s3.eu-north-1.amazonaws.com/products/red1.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJD%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJGMEQCIDinurI70tcQK0d7dt8dijV%2FZwTBZpsgVJb%2Fdjda%2FcbyAiAXDhGizXD7j%2BwOChu7LBmddZnNRTM7qSXqvitX9YjuSCrtAgiJ%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDQ3MDU1MTYwMjc5MiIMq2m%2FmrJrx5oJUDchKsECbR%2BsX5HmqaphKxymdvhLNdgv8%2B8JK1pDhAhyOVEcOe1p0rdfIUduIRbxMj8y7oqQB5uo%2BzPDDs4t55dMzFFOHVmdO4XCQ08PWUrYZvN5%2FKhLJ5Hy5WUVLhMwbzb3sawek1uJJfxg7rJGlURufl%2FglN1CYpiNElLAGemAQ5pXZmdMdQb9yvgyBsVHqepIHrDlfG0HNCxJ6vXhgBEEKtvvTvo%2BKQ7TqJ06IaLa%2FXq2FdOcmmKHxGId54zkq4zL8OYeJEm%2F%2FI0YriVuPO19D1b8yxqtCVqLaDv8lqfMdvLQZ5m%2FzAtWtLRcFfRFwbfqe4hCqylhZ3Z4H4RduNbc5yg1xQVrn1ccSO2obaDpEkREz24bT3oFo1O2RCKkoKMatjMRKSg8zduChgVwdntFlL9gbBL5651S0CeUMdHbWNjvZOyxMNbTg6IGOrQCF503WO7%2FjkiYjMh05xGa99yqLPgPNzG2NYa2n549%2BOhysw84LbR55zBkBNR1MBjKoPtD24PDkNDsp%2BzvOnjGnsN9%2FOeUIA1GFjA0s6s%2B95yHR8w6ifzRJTV4cbtujeCd1n8UCJuc7Ac%2FvpEL%2B2dHISQas6dbbXiaAA%2F1bAP29MGPSfr%2F5l4KGpE%2FVXG3XGvXvEnf2k%2B4XifVIY50GQJZb702YStJ8dcD9AIPxek5LruYYdVdjInO%2BuDmJyGhC5HKNDizeRi4FykbuRHPY%2B2VnVm0lyTbw0r%2BeV6otPgaiiTOdtYhoRbdcOBRwv2DqYjFsnhGmRxAoXwCaWc0%2BJQGjx3O8L6uwyWnJ%2F1QDmKzBcXm8p5UxKGhmx9MC44TPWvT3AkNlPCOvvzhrOfdEBYaFSKWR%2Bc%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230420T080645Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43200&X-Amz-Credential=ASIAW3DYPUZUF6FV4DHS%2F20230420%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=48a4f998c47e9ded2a108795511594fd5117337448aee79e30402c35efc48b41");
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
        requestDto.setImageLink("https://wine-shop-images.s3.eu-north-1.amazonaws.com/products/red2.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJD%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJGMEQCIDinurI70tcQK0d7dt8dijV%2FZwTBZpsgVJb%2Fdjda%2FcbyAiAXDhGizXD7j%2BwOChu7LBmddZnNRTM7qSXqvitX9YjuSCrtAgiJ%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDQ3MDU1MTYwMjc5MiIMq2m%2FmrJrx5oJUDchKsECbR%2BsX5HmqaphKxymdvhLNdgv8%2B8JK1pDhAhyOVEcOe1p0rdfIUduIRbxMj8y7oqQB5uo%2BzPDDs4t55dMzFFOHVmdO4XCQ08PWUrYZvN5%2FKhLJ5Hy5WUVLhMwbzb3sawek1uJJfxg7rJGlURufl%2FglN1CYpiNElLAGemAQ5pXZmdMdQb9yvgyBsVHqepIHrDlfG0HNCxJ6vXhgBEEKtvvTvo%2BKQ7TqJ06IaLa%2FXq2FdOcmmKHxGId54zkq4zL8OYeJEm%2F%2FI0YriVuPO19D1b8yxqtCVqLaDv8lqfMdvLQZ5m%2FzAtWtLRcFfRFwbfqe4hCqylhZ3Z4H4RduNbc5yg1xQVrn1ccSO2obaDpEkREz24bT3oFo1O2RCKkoKMatjMRKSg8zduChgVwdntFlL9gbBL5651S0CeUMdHbWNjvZOyxMNbTg6IGOrQCF503WO7%2FjkiYjMh05xGa99yqLPgPNzG2NYa2n549%2BOhysw84LbR55zBkBNR1MBjKoPtD24PDkNDsp%2BzvOnjGnsN9%2FOeUIA1GFjA0s6s%2B95yHR8w6ifzRJTV4cbtujeCd1n8UCJuc7Ac%2FvpEL%2B2dHISQas6dbbXiaAA%2F1bAP29MGPSfr%2F5l4KGpE%2FVXG3XGvXvEnf2k%2B4XifVIY50GQJZb702YStJ8dcD9AIPxek5LruYYdVdjInO%2BuDmJyGhC5HKNDizeRi4FykbuRHPY%2B2VnVm0lyTbw0r%2BeV6otPgaiiTOdtYhoRbdcOBRwv2DqYjFsnhGmRxAoXwCaWc0%2BJQGjx3O8L6uwyWnJ%2F1QDmKzBcXm8p5UxKGhmx9MC44TPWvT3AkNlPCOvvzhrOfdEBYaFSKWR%2Bc%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230420T080756Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43200&X-Amz-Credential=ASIAW3DYPUZUF6FV4DHS%2F20230420%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=91a6214ee0db11bb719c79ccd14589cc63fa5f5fc63273c2b0210f8dd5d60ca6");
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
        requestDto.setImageLink("https://wine-shop-images.s3.eu-north-1.amazonaws.com/products/red3.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJD%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJGMEQCIDinurI70tcQK0d7dt8dijV%2FZwTBZpsgVJb%2Fdjda%2FcbyAiAXDhGizXD7j%2BwOChu7LBmddZnNRTM7qSXqvitX9YjuSCrtAgiJ%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDQ3MDU1MTYwMjc5MiIMq2m%2FmrJrx5oJUDchKsECbR%2BsX5HmqaphKxymdvhLNdgv8%2B8JK1pDhAhyOVEcOe1p0rdfIUduIRbxMj8y7oqQB5uo%2BzPDDs4t55dMzFFOHVmdO4XCQ08PWUrYZvN5%2FKhLJ5Hy5WUVLhMwbzb3sawek1uJJfxg7rJGlURufl%2FglN1CYpiNElLAGemAQ5pXZmdMdQb9yvgyBsVHqepIHrDlfG0HNCxJ6vXhgBEEKtvvTvo%2BKQ7TqJ06IaLa%2FXq2FdOcmmKHxGId54zkq4zL8OYeJEm%2F%2FI0YriVuPO19D1b8yxqtCVqLaDv8lqfMdvLQZ5m%2FzAtWtLRcFfRFwbfqe4hCqylhZ3Z4H4RduNbc5yg1xQVrn1ccSO2obaDpEkREz24bT3oFo1O2RCKkoKMatjMRKSg8zduChgVwdntFlL9gbBL5651S0CeUMdHbWNjvZOyxMNbTg6IGOrQCF503WO7%2FjkiYjMh05xGa99yqLPgPNzG2NYa2n549%2BOhysw84LbR55zBkBNR1MBjKoPtD24PDkNDsp%2BzvOnjGnsN9%2FOeUIA1GFjA0s6s%2B95yHR8w6ifzRJTV4cbtujeCd1n8UCJuc7Ac%2FvpEL%2B2dHISQas6dbbXiaAA%2F1bAP29MGPSfr%2F5l4KGpE%2FVXG3XGvXvEnf2k%2B4XifVIY50GQJZb702YStJ8dcD9AIPxek5LruYYdVdjInO%2BuDmJyGhC5HKNDizeRi4FykbuRHPY%2B2VnVm0lyTbw0r%2BeV6otPgaiiTOdtYhoRbdcOBRwv2DqYjFsnhGmRxAoXwCaWc0%2BJQGjx3O8L6uwyWnJ%2F1QDmKzBcXm8p5UxKGhmx9MC44TPWvT3AkNlPCOvvzhrOfdEBYaFSKWR%2Bc%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230420T080831Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43199&X-Amz-Credential=ASIAW3DYPUZUF6FV4DHS%2F20230420%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=a7508b46427fe7eb4d711cf77ce5695a4ab324aaae921e4e9cce2fd4ec3af29f");
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
        requestDto.setImageLink("https://wine-shop-images.s3.eu-north-1.amazonaws.com/products/red4.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJD%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJGMEQCIDinurI70tcQK0d7dt8dijV%2FZwTBZpsgVJb%2Fdjda%2FcbyAiAXDhGizXD7j%2BwOChu7LBmddZnNRTM7qSXqvitX9YjuSCrtAgiJ%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDQ3MDU1MTYwMjc5MiIMq2m%2FmrJrx5oJUDchKsECbR%2BsX5HmqaphKxymdvhLNdgv8%2B8JK1pDhAhyOVEcOe1p0rdfIUduIRbxMj8y7oqQB5uo%2BzPDDs4t55dMzFFOHVmdO4XCQ08PWUrYZvN5%2FKhLJ5Hy5WUVLhMwbzb3sawek1uJJfxg7rJGlURufl%2FglN1CYpiNElLAGemAQ5pXZmdMdQb9yvgyBsVHqepIHrDlfG0HNCxJ6vXhgBEEKtvvTvo%2BKQ7TqJ06IaLa%2FXq2FdOcmmKHxGId54zkq4zL8OYeJEm%2F%2FI0YriVuPO19D1b8yxqtCVqLaDv8lqfMdvLQZ5m%2FzAtWtLRcFfRFwbfqe4hCqylhZ3Z4H4RduNbc5yg1xQVrn1ccSO2obaDpEkREz24bT3oFo1O2RCKkoKMatjMRKSg8zduChgVwdntFlL9gbBL5651S0CeUMdHbWNjvZOyxMNbTg6IGOrQCF503WO7%2FjkiYjMh05xGa99yqLPgPNzG2NYa2n549%2BOhysw84LbR55zBkBNR1MBjKoPtD24PDkNDsp%2BzvOnjGnsN9%2FOeUIA1GFjA0s6s%2B95yHR8w6ifzRJTV4cbtujeCd1n8UCJuc7Ac%2FvpEL%2B2dHISQas6dbbXiaAA%2F1bAP29MGPSfr%2F5l4KGpE%2FVXG3XGvXvEnf2k%2B4XifVIY50GQJZb702YStJ8dcD9AIPxek5LruYYdVdjInO%2BuDmJyGhC5HKNDizeRi4FykbuRHPY%2B2VnVm0lyTbw0r%2BeV6otPgaiiTOdtYhoRbdcOBRwv2DqYjFsnhGmRxAoXwCaWc0%2BJQGjx3O8L6uwyWnJ%2F1QDmKzBcXm8p5UxKGhmx9MC44TPWvT3AkNlPCOvvzhrOfdEBYaFSKWR%2Bc%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230420T080902Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43200&X-Amz-Credential=ASIAW3DYPUZUF6FV4DHS%2F20230420%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=ff0b6fbf9481d68005d811f4df75d007ce1e0e422a87c69c147fcab8bd645d2d");
        List<String> wineDishPairing = List.of("Cheese", "Meat", "Sausages", "Strong dishes");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
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
        requestDto.setTaste("Red crianza wine of garnet red hue with hints of russet. First impression is pleasant with a nice aroma. Powerful and complex, has some spices and varietal aromas, rounded because of the oak. In the mouth is warm, good tannin presence but balanced with the acidity. With round body and a long and tasty after taste. Well-balanced, powerful, full body, persistent, and nice tannin balance.");
        requestDto.setTemperature("Serve between 16-18ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_PRESENT.name());
        requestDto.setPairing("This wine is perfect with cured cheeses, red meats, sausages and strong dishes.");
        requestDto.setImageLink("https://wine-shop-images.s3.eu-north-1.amazonaws.com/products/red5.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJD%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJGMEQCIDinurI70tcQK0d7dt8dijV%2FZwTBZpsgVJb%2Fdjda%2FcbyAiAXDhGizXD7j%2BwOChu7LBmddZnNRTM7qSXqvitX9YjuSCrtAgiJ%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDQ3MDU1MTYwMjc5MiIMq2m%2FmrJrx5oJUDchKsECbR%2BsX5HmqaphKxymdvhLNdgv8%2B8JK1pDhAhyOVEcOe1p0rdfIUduIRbxMj8y7oqQB5uo%2BzPDDs4t55dMzFFOHVmdO4XCQ08PWUrYZvN5%2FKhLJ5Hy5WUVLhMwbzb3sawek1uJJfxg7rJGlURufl%2FglN1CYpiNElLAGemAQ5pXZmdMdQb9yvgyBsVHqepIHrDlfG0HNCxJ6vXhgBEEKtvvTvo%2BKQ7TqJ06IaLa%2FXq2FdOcmmKHxGId54zkq4zL8OYeJEm%2F%2FI0YriVuPO19D1b8yxqtCVqLaDv8lqfMdvLQZ5m%2FzAtWtLRcFfRFwbfqe4hCqylhZ3Z4H4RduNbc5yg1xQVrn1ccSO2obaDpEkREz24bT3oFo1O2RCKkoKMatjMRKSg8zduChgVwdntFlL9gbBL5651S0CeUMdHbWNjvZOyxMNbTg6IGOrQCF503WO7%2FjkiYjMh05xGa99yqLPgPNzG2NYa2n549%2BOhysw84LbR55zBkBNR1MBjKoPtD24PDkNDsp%2BzvOnjGnsN9%2FOeUIA1GFjA0s6s%2B95yHR8w6ifzRJTV4cbtujeCd1n8UCJuc7Ac%2FvpEL%2B2dHISQas6dbbXiaAA%2F1bAP29MGPSfr%2F5l4KGpE%2FVXG3XGvXvEnf2k%2B4XifVIY50GQJZb702YStJ8dcD9AIPxek5LruYYdVdjInO%2BuDmJyGhC5HKNDizeRi4FykbuRHPY%2B2VnVm0lyTbw0r%2BeV6otPgaiiTOdtYhoRbdcOBRwv2DqYjFsnhGmRxAoXwCaWc0%2BJQGjx3O8L6uwyWnJ%2F1QDmKzBcXm8p5UxKGhmx9MC44TPWvT3AkNlPCOvvzhrOfdEBYaFSKWR%2Bc%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230420T080935Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43200&X-Amz-Credential=ASIAW3DYPUZUF6FV4DHS%2F20230420%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=afbd7f89be0ceeb8b291e5c5d34d7d5c9c05c6842b9dbc0644534699b46e1d0b");
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
        requestDto.setImageLink("https://wine-shop-images.s3.eu-north-1.amazonaws.com/products/red6.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJD%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJGMEQCIDinurI70tcQK0d7dt8dijV%2FZwTBZpsgVJb%2Fdjda%2FcbyAiAXDhGizXD7j%2BwOChu7LBmddZnNRTM7qSXqvitX9YjuSCrtAgiJ%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDQ3MDU1MTYwMjc5MiIMq2m%2FmrJrx5oJUDchKsECbR%2BsX5HmqaphKxymdvhLNdgv8%2B8JK1pDhAhyOVEcOe1p0rdfIUduIRbxMj8y7oqQB5uo%2BzPDDs4t55dMzFFOHVmdO4XCQ08PWUrYZvN5%2FKhLJ5Hy5WUVLhMwbzb3sawek1uJJfxg7rJGlURufl%2FglN1CYpiNElLAGemAQ5pXZmdMdQb9yvgyBsVHqepIHrDlfG0HNCxJ6vXhgBEEKtvvTvo%2BKQ7TqJ06IaLa%2FXq2FdOcmmKHxGId54zkq4zL8OYeJEm%2F%2FI0YriVuPO19D1b8yxqtCVqLaDv8lqfMdvLQZ5m%2FzAtWtLRcFfRFwbfqe4hCqylhZ3Z4H4RduNbc5yg1xQVrn1ccSO2obaDpEkREz24bT3oFo1O2RCKkoKMatjMRKSg8zduChgVwdntFlL9gbBL5651S0CeUMdHbWNjvZOyxMNbTg6IGOrQCF503WO7%2FjkiYjMh05xGa99yqLPgPNzG2NYa2n549%2BOhysw84LbR55zBkBNR1MBjKoPtD24PDkNDsp%2BzvOnjGnsN9%2FOeUIA1GFjA0s6s%2B95yHR8w6ifzRJTV4cbtujeCd1n8UCJuc7Ac%2FvpEL%2B2dHISQas6dbbXiaAA%2F1bAP29MGPSfr%2F5l4KGpE%2FVXG3XGvXvEnf2k%2B4XifVIY50GQJZb702YStJ8dcD9AIPxek5LruYYdVdjInO%2BuDmJyGhC5HKNDizeRi4FykbuRHPY%2B2VnVm0lyTbw0r%2BeV6otPgaiiTOdtYhoRbdcOBRwv2DqYjFsnhGmRxAoXwCaWc0%2BJQGjx3O8L6uwyWnJ%2F1QDmKzBcXm8p5UxKGhmx9MC44TPWvT3AkNlPCOvvzhrOfdEBYaFSKWR%2Bc%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230420T081005Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43199&X-Amz-Credential=ASIAW3DYPUZUF6FV4DHS%2F20230420%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=4eedf1d33553f36bf05e567e38b171565d65d140c6c0845e6c0499a995281982");
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
        requestDto.setImageLink("https://wine-shop-images.s3.eu-north-1.amazonaws.com/products/white1.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJD%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJGMEQCIDinurI70tcQK0d7dt8dijV%2FZwTBZpsgVJb%2Fdjda%2FcbyAiAXDhGizXD7j%2BwOChu7LBmddZnNRTM7qSXqvitX9YjuSCrtAgiJ%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDQ3MDU1MTYwMjc5MiIMq2m%2FmrJrx5oJUDchKsECbR%2BsX5HmqaphKxymdvhLNdgv8%2B8JK1pDhAhyOVEcOe1p0rdfIUduIRbxMj8y7oqQB5uo%2BzPDDs4t55dMzFFOHVmdO4XCQ08PWUrYZvN5%2FKhLJ5Hy5WUVLhMwbzb3sawek1uJJfxg7rJGlURufl%2FglN1CYpiNElLAGemAQ5pXZmdMdQb9yvgyBsVHqepIHrDlfG0HNCxJ6vXhgBEEKtvvTvo%2BKQ7TqJ06IaLa%2FXq2FdOcmmKHxGId54zkq4zL8OYeJEm%2F%2FI0YriVuPO19D1b8yxqtCVqLaDv8lqfMdvLQZ5m%2FzAtWtLRcFfRFwbfqe4hCqylhZ3Z4H4RduNbc5yg1xQVrn1ccSO2obaDpEkREz24bT3oFo1O2RCKkoKMatjMRKSg8zduChgVwdntFlL9gbBL5651S0CeUMdHbWNjvZOyxMNbTg6IGOrQCF503WO7%2FjkiYjMh05xGa99yqLPgPNzG2NYa2n549%2BOhysw84LbR55zBkBNR1MBjKoPtD24PDkNDsp%2BzvOnjGnsN9%2FOeUIA1GFjA0s6s%2B95yHR8w6ifzRJTV4cbtujeCd1n8UCJuc7Ac%2FvpEL%2B2dHISQas6dbbXiaAA%2F1bAP29MGPSfr%2F5l4KGpE%2FVXG3XGvXvEnf2k%2B4XifVIY50GQJZb702YStJ8dcD9AIPxek5LruYYdVdjInO%2BuDmJyGhC5HKNDizeRi4FykbuRHPY%2B2VnVm0lyTbw0r%2BeV6otPgaiiTOdtYhoRbdcOBRwv2DqYjFsnhGmRxAoXwCaWc0%2BJQGjx3O8L6uwyWnJ%2F1QDmKzBcXm8p5UxKGhmx9MC44TPWvT3AkNlPCOvvzhrOfdEBYaFSKWR%2Bc%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230420T081042Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43200&X-Amz-Credential=ASIAW3DYPUZUF6FV4DHS%2F20230420%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=ae84590066f258ae625a9a5e7eafee5b306dc99de8f19b0634da300355b637b2");
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
        requestDto.setImageLink("https://wine-shop-images.s3.eu-north-1.amazonaws.com/products/white2.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJD%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJGMEQCIDinurI70tcQK0d7dt8dijV%2FZwTBZpsgVJb%2Fdjda%2FcbyAiAXDhGizXD7j%2BwOChu7LBmddZnNRTM7qSXqvitX9YjuSCrtAgiJ%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDQ3MDU1MTYwMjc5MiIMq2m%2FmrJrx5oJUDchKsECbR%2BsX5HmqaphKxymdvhLNdgv8%2B8JK1pDhAhyOVEcOe1p0rdfIUduIRbxMj8y7oqQB5uo%2BzPDDs4t55dMzFFOHVmdO4XCQ08PWUrYZvN5%2FKhLJ5Hy5WUVLhMwbzb3sawek1uJJfxg7rJGlURufl%2FglN1CYpiNElLAGemAQ5pXZmdMdQb9yvgyBsVHqepIHrDlfG0HNCxJ6vXhgBEEKtvvTvo%2BKQ7TqJ06IaLa%2FXq2FdOcmmKHxGId54zkq4zL8OYeJEm%2F%2FI0YriVuPO19D1b8yxqtCVqLaDv8lqfMdvLQZ5m%2FzAtWtLRcFfRFwbfqe4hCqylhZ3Z4H4RduNbc5yg1xQVrn1ccSO2obaDpEkREz24bT3oFo1O2RCKkoKMatjMRKSg8zduChgVwdntFlL9gbBL5651S0CeUMdHbWNjvZOyxMNbTg6IGOrQCF503WO7%2FjkiYjMh05xGa99yqLPgPNzG2NYa2n549%2BOhysw84LbR55zBkBNR1MBjKoPtD24PDkNDsp%2BzvOnjGnsN9%2FOeUIA1GFjA0s6s%2B95yHR8w6ifzRJTV4cbtujeCd1n8UCJuc7Ac%2FvpEL%2B2dHISQas6dbbXiaAA%2F1bAP29MGPSfr%2F5l4KGpE%2FVXG3XGvXvEnf2k%2B4XifVIY50GQJZb702YStJ8dcD9AIPxek5LruYYdVdjInO%2BuDmJyGhC5HKNDizeRi4FykbuRHPY%2B2VnVm0lyTbw0r%2BeV6otPgaiiTOdtYhoRbdcOBRwv2DqYjFsnhGmRxAoXwCaWc0%2BJQGjx3O8L6uwyWnJ%2F1QDmKzBcXm8p5UxKGhmx9MC44TPWvT3AkNlPCOvvzhrOfdEBYaFSKWR%2Bc%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230420T081119Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43200&X-Amz-Credential=ASIAW3DYPUZUF6FV4DHS%2F20230420%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=e4a39cc0b434ecb2cb6841bbfb00451da90c7ede2fc28e2209635e2783da0d0a");
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
        requestDto.setImageLink("https://wine-shop-images.s3.eu-north-1.amazonaws.com/products/white3.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJD%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJGMEQCIDinurI70tcQK0d7dt8dijV%2FZwTBZpsgVJb%2Fdjda%2FcbyAiAXDhGizXD7j%2BwOChu7LBmddZnNRTM7qSXqvitX9YjuSCrtAgiJ%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDQ3MDU1MTYwMjc5MiIMq2m%2FmrJrx5oJUDchKsECbR%2BsX5HmqaphKxymdvhLNdgv8%2B8JK1pDhAhyOVEcOe1p0rdfIUduIRbxMj8y7oqQB5uo%2BzPDDs4t55dMzFFOHVmdO4XCQ08PWUrYZvN5%2FKhLJ5Hy5WUVLhMwbzb3sawek1uJJfxg7rJGlURufl%2FglN1CYpiNElLAGemAQ5pXZmdMdQb9yvgyBsVHqepIHrDlfG0HNCxJ6vXhgBEEKtvvTvo%2BKQ7TqJ06IaLa%2FXq2FdOcmmKHxGId54zkq4zL8OYeJEm%2F%2FI0YriVuPO19D1b8yxqtCVqLaDv8lqfMdvLQZ5m%2FzAtWtLRcFfRFwbfqe4hCqylhZ3Z4H4RduNbc5yg1xQVrn1ccSO2obaDpEkREz24bT3oFo1O2RCKkoKMatjMRKSg8zduChgVwdntFlL9gbBL5651S0CeUMdHbWNjvZOyxMNbTg6IGOrQCF503WO7%2FjkiYjMh05xGa99yqLPgPNzG2NYa2n549%2BOhysw84LbR55zBkBNR1MBjKoPtD24PDkNDsp%2BzvOnjGnsN9%2FOeUIA1GFjA0s6s%2B95yHR8w6ifzRJTV4cbtujeCd1n8UCJuc7Ac%2FvpEL%2B2dHISQas6dbbXiaAA%2F1bAP29MGPSfr%2F5l4KGpE%2FVXG3XGvXvEnf2k%2B4XifVIY50GQJZb702YStJ8dcD9AIPxek5LruYYdVdjInO%2BuDmJyGhC5HKNDizeRi4FykbuRHPY%2B2VnVm0lyTbw0r%2BeV6otPgaiiTOdtYhoRbdcOBRwv2DqYjFsnhGmRxAoXwCaWc0%2BJQGjx3O8L6uwyWnJ%2F1QDmKzBcXm8p5UxKGhmx9MC44TPWvT3AkNlPCOvvzhrOfdEBYaFSKWR%2Bc%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230420T081156Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43200&X-Amz-Credential=ASIAW3DYPUZUF6FV4DHS%2F20230420%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=e6fd14227d420a256b2d6516fc2e3542e3a4751cae7a5f28ba592ae9925c3f14");
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
        requestDto.setImageLink("https://wine-shop-images.s3.eu-north-1.amazonaws.com/products/white4.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJD%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJGMEQCIDinurI70tcQK0d7dt8dijV%2FZwTBZpsgVJb%2Fdjda%2FcbyAiAXDhGizXD7j%2BwOChu7LBmddZnNRTM7qSXqvitX9YjuSCrtAgiJ%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDQ3MDU1MTYwMjc5MiIMq2m%2FmrJrx5oJUDchKsECbR%2BsX5HmqaphKxymdvhLNdgv8%2B8JK1pDhAhyOVEcOe1p0rdfIUduIRbxMj8y7oqQB5uo%2BzPDDs4t55dMzFFOHVmdO4XCQ08PWUrYZvN5%2FKhLJ5Hy5WUVLhMwbzb3sawek1uJJfxg7rJGlURufl%2FglN1CYpiNElLAGemAQ5pXZmdMdQb9yvgyBsVHqepIHrDlfG0HNCxJ6vXhgBEEKtvvTvo%2BKQ7TqJ06IaLa%2FXq2FdOcmmKHxGId54zkq4zL8OYeJEm%2F%2FI0YriVuPO19D1b8yxqtCVqLaDv8lqfMdvLQZ5m%2FzAtWtLRcFfRFwbfqe4hCqylhZ3Z4H4RduNbc5yg1xQVrn1ccSO2obaDpEkREz24bT3oFo1O2RCKkoKMatjMRKSg8zduChgVwdntFlL9gbBL5651S0CeUMdHbWNjvZOyxMNbTg6IGOrQCF503WO7%2FjkiYjMh05xGa99yqLPgPNzG2NYa2n549%2BOhysw84LbR55zBkBNR1MBjKoPtD24PDkNDsp%2BzvOnjGnsN9%2FOeUIA1GFjA0s6s%2B95yHR8w6ifzRJTV4cbtujeCd1n8UCJuc7Ac%2FvpEL%2B2dHISQas6dbbXiaAA%2F1bAP29MGPSfr%2F5l4KGpE%2FVXG3XGvXvEnf2k%2B4XifVIY50GQJZb702YStJ8dcD9AIPxek5LruYYdVdjInO%2BuDmJyGhC5HKNDizeRi4FykbuRHPY%2B2VnVm0lyTbw0r%2BeV6otPgaiiTOdtYhoRbdcOBRwv2DqYjFsnhGmRxAoXwCaWc0%2BJQGjx3O8L6uwyWnJ%2F1QDmKzBcXm8p5UxKGhmx9MC44TPWvT3AkNlPCOvvzhrOfdEBYaFSKWR%2Bc%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230420T081228Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43200&X-Amz-Credential=ASIAW3DYPUZUF6FV4DHS%2F20230420%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=8e68de3271bbc5a626bf047e2626ab473177bec86862d1893cb44afc395789dd");
        List<String> wineDishPairing = List.of("Cheese", "Meat", "Sausages", "Strong dishes");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
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
        requestDto.setTaste("Pale yellow flecked with gold, with a steady stream of small bubbles forming beads. First impression: good, powerful and refined; aromas from bottle-ageing, as well as ripe fruit and flowers, can be detected. It is round, pleasant, long-lingering and fresh on the palate. Well-balanced, harmonious, direct, dry, with a long-lingering and pleasant finish.");
        requestDto.setTemperature("Serve between 6-8ºC");
        requestDto.setEvent(ProductEvent.Event.FOR_SPECIAL_EVENTS.name());
        requestDto.setPairing("Perfect for salads, vegetables, fishes and mild cheeses. It can also be taken as an aperitif.");
        requestDto.setImageLink("https://wine-shop-images.s3.eu-north-1.amazonaws.com/products/white5.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJD%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJGMEQCIDinurI70tcQK0d7dt8dijV%2FZwTBZpsgVJb%2Fdjda%2FcbyAiAXDhGizXD7j%2BwOChu7LBmddZnNRTM7qSXqvitX9YjuSCrtAgiJ%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDQ3MDU1MTYwMjc5MiIMq2m%2FmrJrx5oJUDchKsECbR%2BsX5HmqaphKxymdvhLNdgv8%2B8JK1pDhAhyOVEcOe1p0rdfIUduIRbxMj8y7oqQB5uo%2BzPDDs4t55dMzFFOHVmdO4XCQ08PWUrYZvN5%2FKhLJ5Hy5WUVLhMwbzb3sawek1uJJfxg7rJGlURufl%2FglN1CYpiNElLAGemAQ5pXZmdMdQb9yvgyBsVHqepIHrDlfG0HNCxJ6vXhgBEEKtvvTvo%2BKQ7TqJ06IaLa%2FXq2FdOcmmKHxGId54zkq4zL8OYeJEm%2F%2FI0YriVuPO19D1b8yxqtCVqLaDv8lqfMdvLQZ5m%2FzAtWtLRcFfRFwbfqe4hCqylhZ3Z4H4RduNbc5yg1xQVrn1ccSO2obaDpEkREz24bT3oFo1O2RCKkoKMatjMRKSg8zduChgVwdntFlL9gbBL5651S0CeUMdHbWNjvZOyxMNbTg6IGOrQCF503WO7%2FjkiYjMh05xGa99yqLPgPNzG2NYa2n549%2BOhysw84LbR55zBkBNR1MBjKoPtD24PDkNDsp%2BzvOnjGnsN9%2FOeUIA1GFjA0s6s%2B95yHR8w6ifzRJTV4cbtujeCd1n8UCJuc7Ac%2FvpEL%2B2dHISQas6dbbXiaAA%2F1bAP29MGPSfr%2F5l4KGpE%2FVXG3XGvXvEnf2k%2B4XifVIY50GQJZb702YStJ8dcD9AIPxek5LruYYdVdjInO%2BuDmJyGhC5HKNDizeRi4FykbuRHPY%2B2VnVm0lyTbw0r%2BeV6otPgaiiTOdtYhoRbdcOBRwv2DqYjFsnhGmRxAoXwCaWc0%2BJQGjx3O8L6uwyWnJ%2F1QDmKzBcXm8p5UxKGhmx9MC44TPWvT3AkNlPCOvvzhrOfdEBYaFSKWR%2Bc%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230420T081258Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43200&X-Amz-Credential=ASIAW3DYPUZUF6FV4DHS%2F20230420%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=5750316cf62ff965a1db546dffe3c038e2c5f7ba6c472233e623457b9ce3b55e");
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
        requestDto.setImageLink("https://wine-shop-images.s3.eu-north-1.amazonaws.com/products/white6.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJD%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDGV1LWNlbnRyYWwtMSJGMEQCIDinurI70tcQK0d7dt8dijV%2FZwTBZpsgVJb%2Fdjda%2FcbyAiAXDhGizXD7j%2BwOChu7LBmddZnNRTM7qSXqvitX9YjuSCrtAgiJ%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDQ3MDU1MTYwMjc5MiIMq2m%2FmrJrx5oJUDchKsECbR%2BsX5HmqaphKxymdvhLNdgv8%2B8JK1pDhAhyOVEcOe1p0rdfIUduIRbxMj8y7oqQB5uo%2BzPDDs4t55dMzFFOHVmdO4XCQ08PWUrYZvN5%2FKhLJ5Hy5WUVLhMwbzb3sawek1uJJfxg7rJGlURufl%2FglN1CYpiNElLAGemAQ5pXZmdMdQb9yvgyBsVHqepIHrDlfG0HNCxJ6vXhgBEEKtvvTvo%2BKQ7TqJ06IaLa%2FXq2FdOcmmKHxGId54zkq4zL8OYeJEm%2F%2FI0YriVuPO19D1b8yxqtCVqLaDv8lqfMdvLQZ5m%2FzAtWtLRcFfRFwbfqe4hCqylhZ3Z4H4RduNbc5yg1xQVrn1ccSO2obaDpEkREz24bT3oFo1O2RCKkoKMatjMRKSg8zduChgVwdntFlL9gbBL5651S0CeUMdHbWNjvZOyxMNbTg6IGOrQCF503WO7%2FjkiYjMh05xGa99yqLPgPNzG2NYa2n549%2BOhysw84LbR55zBkBNR1MBjKoPtD24PDkNDsp%2BzvOnjGnsN9%2FOeUIA1GFjA0s6s%2B95yHR8w6ifzRJTV4cbtujeCd1n8UCJuc7Ac%2FvpEL%2B2dHISQas6dbbXiaAA%2F1bAP29MGPSfr%2F5l4KGpE%2FVXG3XGvXvEnf2k%2B4XifVIY50GQJZb702YStJ8dcD9AIPxek5LruYYdVdjInO%2BuDmJyGhC5HKNDizeRi4FykbuRHPY%2B2VnVm0lyTbw0r%2BeV6otPgaiiTOdtYhoRbdcOBRwv2DqYjFsnhGmRxAoXwCaWc0%2BJQGjx3O8L6uwyWnJ%2F1QDmKzBcXm8p5UxKGhmx9MC44TPWvT3AkNlPCOvvzhrOfdEBYaFSKWR%2Bc%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20230420T081338Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43199&X-Amz-Credential=ASIAW3DYPUZUF6FV4DHS%2F20230420%2Feu-north-1%2Fs3%2Faws4_request&X-Amz-Signature=9988599c22ae8d7c36741580470e12a5924cb3c8eca65f85a44d8d837d7b6009");
        List<String> wineDishPairing = List.of("Meat", "Fish", "Cheese", "Foie", "Smoked dishes");
        requestDto.setDishes(wineDishPairing);
        create(requestDto);
    }

}
