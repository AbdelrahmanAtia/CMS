import { Injectable } from '@angular/core';
import { Category } from '../_models/Category';
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Config } from '../_models/Config ';
import { Response } from '../_models/Response';
import { ValidationErrors } from '@angular/forms';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  baseUrl: string = new Config().baseUrl;

  constructor(private http: HttpClient) { }

  getAllCategories(): Observable<Category[]> {
    let url: string = this.baseUrl + "/categories/all";
    return this.http.get<Category[]>(url);
  }

  getCategories(searchTerm: string, pageNumber: number): Observable<HttpResponse<Category[]>> {
    pageNumber--; // cause pageNumber starts at zero not 1 according to backend..
    let url: string = this.baseUrl + "/categories";
    return this.http.get<any>(url, {
      params: {
        searchTerm: searchTerm,
        pageNumber: pageNumber.toString(),
        pageSize: new Config().pageSize.toString()
      }
      , observe: 'response'
    });
  }

  getCategory(categoryId: number): Observable<Category> {
    let url: string = this.baseUrl + "/categories/" + categoryId;
    return this.http.get<Category>(url);
  }

  addNewCategory(category: Category): Observable<Category> {
    let url: string = this.baseUrl + "/categories";
    return this.http.post<Category>(url, category);
  }

  updateCategory(category: Category): Observable<Category> {
    let url: string = this.baseUrl + "/categories";
    return this.http.put<Category>(url, category);
  }

  deleteCategory(categoryId: number): Observable<Response> {
    let url: string = this.baseUrl + "/categories/" + categoryId;
    return this.http.delete<Response>(url);
  }

  deleteCategoryImage(categoryName: string): Observable<Response> {
    let url: string = this.baseUrl + "/categories/deleteImage/" + categoryName;
    return this.http.delete<Response>(url);
  }

  isUniqueCategoryName(categoryName: string, categoryId: number): Observable<ValidationErrors> {
    let url: string = this.baseUrl + "/categories/isNameExist/" + categoryName + "/" + categoryId;
    return this.http.get<boolean>(url).pipe(
      map((response: boolean) => {
        return response ? null : { "uniqueCategoryName": true };
      })
    );
  }

}