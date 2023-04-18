package com.project.wineshop.model.enums;

import lombok.Data;

@Data
public class ProductType {

    public enum Type {
        DRY,
        SEMIDRY,
        SEMISWEET,
        SWEET,
        BRUT,
        SPARCLING
    }

}
