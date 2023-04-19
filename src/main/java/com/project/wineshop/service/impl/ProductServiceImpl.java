package com.project.wineshop.service.impl;

import com.project.wineshop.model.Product;
import com.project.wineshop.repository.ProductRepository;
import com.project.wineshop.repository.specification.SpecificationManager;
import com.project.wineshop.service.ProductService;
import com.project.wineshop.utility.PageRequestFormer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final SpecificationManager<Product> productSpecificationManager;

    public ProductServiceImpl(ProductRepository productRepository,
                              SpecificationManager<Product> productSpecificationManager) {
        this.productRepository = productRepository;
        this.productSpecificationManager = productSpecificationManager;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        product.getDishes().size(); // ініціалізуємо колекцію в межах транзакції
        return product;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAll(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest).toList();
    }

    /*
        @Override
        public List<Product> findAll(Map<String, String> params) {
            Specification<Product> specification = null;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                Specification<Product> sp = productSpecificationManager.get(entry.getKey(),
                        entry.getValue().split(","));
                specification =
                    specification == null ? Specification.where(sp) : specification.and(sp);
            }
            return productRepository.findAll(specification);
        }
    */

    @Override
    public List<Product> findAll(Map<String, String> params) {
        Integer page = 0;
        if (params.containsKey("page")) {
            page = Integer.valueOf(params.get("page"));
            params.remove("page");
        }
        Integer size = 6;
        if (params.containsKey("size")) {
            size = Integer.valueOf(params.get("size"));
            params.remove("size");
        }
        String sortBy = "id:ASC";
        if (params.containsKey("sortBy")) {
            sortBy = params.get("sortBy");
            params.remove("sortBy");
        }
        PageRequest pageRequest = PageRequestFormer.formPageRequest(page, size, sortBy);

        /*
                Specification<Product> specification = Specification.where(null);
                if (type != null) {
                    specification = specification.and(ProductSpecification.typeEquals(type));
                }
                if (color != null) {
                    specification = specification.and(ProductSpecification.colorEquals(color));
                }
                if (event != null) {
                    specification = specification.and(ProductSpecification.eventEquals(event));
                }
                if (dish != null) {
                    String[] dishes = dish.split(",");
                    specification = specification.and(ProductSpecification.dishIn(dishes));
                }
        */
        //Map<String, String> filters = new HashMap<>();
        Specification<Product> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<Product> sp = productSpecificationManager.get(entry.getKey(),
                    entry.getValue().split(","));
            specification = specification == null ? Specification.where(sp) : specification.and(sp);
        }
        return productRepository.findAll(specification, pageRequest).toList();
    }

    /*
        @Override
        public List<Product> getProducts_0(String type,
                                         String color,
                                         String event,
                                         String dishes,
                                         String sortBy,
                                         String sortOrder,
                                         Integer page,
                                         Integer size) {
            try {
                List<Specification<Product>> specs = new ArrayList<>();

                if (color != null) {
                    specs.add((root, query, cb) -> cb.equal(root.get("color"), color));
                }

                if (type != null) {
                    specs.add((root, query, cb) -> cb.equal(root.get("type"), type));
                }

                Specification<Product> spec
                    = specs.stream().reduce((a, b) -> a.and(b)).orElse(null);

                Sort sort = null;
                if (sortBy != null) {
                    String[] sortParams = sortBy.split(";");
                    List<Sort.Order> orders = new ArrayList<>();
                    for (String sortParam : sortParams) {
                        String[] params = sortParam.split(":");
                        Sort.Direction direction
                            = params[1].equalsIgnoreCase("asc")
                                ? Sort.Direction.ASC : Sort.Direction.DESC;
                        orders.add(new Sort.Order(direction, params[0]));
                    }
                    sort = Sort.by(orders);
                }

                Pageable pagingSort = PageRequest.of(page, size, sort);

                Page<Product> pageProducts;
                if (spec != null) {
                    pageProducts = productService.findAll(spec, pagingSort);
                } else {
                    pageProducts = productService.findAll(pagingSort);
                }

                List<Product> products = pageProducts.getContent();

                Map<String, Object> response = new HashMap<>();
                response.put("products", products);
                response.put("currentPage", pageProducts.getNumber());
                response.put("totalItems", pageProducts.getTotalElements());
                response.put("totalPages", pageProducts.getTotalPages());

                return new ResponseEntity<>(products, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    */

    /*
        @Override
        public List<Product> getProducts_1(String type,
                                         String color,
                                         String event,
                                         String dishes,
                                         String sortBy,
                                         Integer page,
                                         Integer size) {
            Pageable pageable = PageRequest.of(page, size, getSort(sortBy));
            Specification<Product> spec = Specification.where(null);

            if (type != null) {
                spec = spec.and(ProductSpecifications.hasType(type));
            }

            if (color != null) {
                spec = spec.and(ProductSpecifications.hasColor(color));
            }

            if (event != null) {
                spec = spec.and(ProductSpecifications.hasEvent(event));
            }

            if (dishes != null) {
                String[] dishesArr = dishes.split(",");
                for (String dish : dishesArr) {
                    spec = spec.and(ProductSpecifications.hasDish(dish));
                }
            }

            return productRepository.findAll(spec, pageable).getContent();
        }
    */

    private Sort getSort(String sortBy) {
        String[] sortArray = sortBy.split(";");
        List<Sort.Order> orders = new ArrayList<>();
        for (String sortProperty : sortArray) {
            String[] parts = sortProperty.split(":");
            String property = parts[0];
            Sort.Direction direction = Sort.Direction.ASC;
            if (parts.length > 1 && parts[1].equalsIgnoreCase("DESC")) {
                direction = Sort.Direction.DESC;
            }
            orders.add(new Sort.Order(direction, property));
        }

        return Sort.by(orders);
    }

    @Override
    public Product update(Long id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
