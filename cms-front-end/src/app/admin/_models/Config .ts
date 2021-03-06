export class Config {

    accessToken: string;
    baseUrl: string;
    pageSize: number;
    tax: number;
    superAdminUserId: number;

    constructor() {
        this.accessToken = "278f8106299f3cea0a70d7ac69b0781ada4c8414";
        this.baseUrl = "http://localhost:8080/cms/rest/api";
        this.pageSize = 3;
        this.tax = 0.1;
        this.superAdminUserId = 1;
    }

    static categoriesImagesBaseURL = "http://localhost:8080/cms/rest/api/categories/categories_images";
    static productsImagesBaseURL = "http://localhost:8080/cms/rest/api/products/products_images";

    //dash-board component
    static dashBoardRoute = ['/admin/main/dashboard'];

    //order component
    static orderListRoute = ['/admin/main', 'orders', 'ALL', '1'];
    static addNewOrderRoute = ['/admin/main', 'orders', 'new'];

    //product component
    static productListRoute = ['/admin/main', 'products', ' ', 0, 1];
    static addNewProductRoute = ['/admin/main', 'products', 'new'];

    //category component
    static categoryListRoute = ['/admin/main', 'categories', ' ', 1];
    static addNewCategoryRoute = ['/admin/main', 'categories', 'new'];
    
    //user component
    static userListRoute = ['/admin/main', 'users', ' ', "All", 1];
    
    //profile component
    static profileRoute = ['/admin/main', 'profile'];
    
    //login component
    static loginRoute = ['/admin/login'];

    // client categories component
    static clientCategoriesRoute = ['/client/categories'];  
    
    //client cart component
    static clientCartRoute = ['/client/cart']; 

    //client order preview component
    static clientOrderPreviewRoute = ['/client/order/preview']; 

    static clientProductsRoute(categoryId: number) {
        return ['/client/categories/' + categoryId + '/products'];
    }

}