/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanht.utils;

/**
 *
 * @author Nhan
 */
public class MyApplicationConstants {

    public class DispatchFeatures {

        public static final String LOGIN_PAGE = "loginPage";
        public static final String LOGIN_CONTROLLER = "loginController";
        public static final String STARTUP_CONTROLLER = "";
    }

    public class SearchFeatures {

        public static final String STATIC_SEARCH_PAGE = "staticSearchPage";
        public static final String SEARCH_PAGE = "searchPage";
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchController";
    }

    public class DeteleFeatures {

        public static final String DELETE_ACCOUNT_CONTROLLER = "deleteController";
    }

    public class CartFeatures {

        public static final String ADD_BOOK_TO_CART_CONTROLLER = "addBookToCartController";
        public static final String REMOVE_ITEMS_FROM_CART = "removeItemFromCartController";
        public static final String SHOW_CART_PAGE = "cartPage";
        public static final String CART_CONTROLLER = "cartController";
        public static final String CHECKOUT_CONTROLLER = "checkoutController";
    }

    public class SignUpFeatures {

        public static final String CREATE_ACCOUNT_CONTROLLER = "createAccountController";
        public static final String CREATE_ACCOUNT_PAGE = "registerPage";
    }

    public class UpdateFeatures {

        public static final String UPDATE_CONTROLLER = "updateAccountController";
    }

    public class ShoppingFeatures {

        public static final String SHOPPING_PAGE = "shoppingPage";
    }

    public class ErrorFeatures {

        public static final String ERROR_PAGE = "errorPage";
        public static final String CREATE_ACCOUNT_ERROR_PAGE = "createAccountErrorPage";
        public static final String INVALID_PAGE = "invalidPage";
        public static final String ERROR_PAGE_404 = "404ErrorPage";
        public static final String ERROR_PAGE_500 = "500ErrorPage";
    }

    public class LogoutFeature {

        public static final String LOGOUT_CONTROLLER = "logoutController";
    }

}
