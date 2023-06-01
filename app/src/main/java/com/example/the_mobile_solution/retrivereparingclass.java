package com.example.the_mobile_solution;

public class retrivereparingclass {
    String Product_Name,Company_Name,Problem,Category_Name;
public retrivereparingclass(){}

    public retrivereparingclass(String product_Name, String company_Name, String problem, String category_Name) {
        Product_Name = product_Name;
        Company_Name = company_Name;
        Problem = problem;
        Category_Name = category_Name;
    }

    public String getCategory_Name() {
        return Category_Name;
    }

    public void setCategory_Name(String category_Name) {
        Category_Name = category_Name;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public void setProduct_Name(String product_Name) {
        Product_Name = product_Name;
    }

    public String getCompany_Name() {
        return Company_Name;
    }

    public void setCompany_Name(String company_Name) {
        Company_Name = company_Name;
    }

    public String getProblem() {
        return Problem;
    }

    public void setProblem(String problem) {
        Problem = problem;
    }
}
