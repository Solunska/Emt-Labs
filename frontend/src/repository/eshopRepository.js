import axios from '../custom-axios/axios';

const EShopService = {
    fetchCountries: () => {
        return axios.get("/countries")
    },
    fetchCategories: () => {
        return axios.get("/categories")
    },
    fetchBooks: () => {
        return axios.get("/books")
    },
    fetchAuthors: () => {
        return axios.get("/authors")
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    addBook: (name, category, author, availableCopies) => {
        return axios.post(`/books/add`, {
            "name": name,
            "category": category,
            "author": author,
            "availableCopies": availableCopies,
        });
    },
    editBook: (id, name, category, author, availableCopies) => {
        return axios.put(`/books/edit/${id}`, {
            "name": name,
            "category": category,
            "author": author,
            "availableCopies": availableCopies,
        });
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
    markAsTaken: (id) => {
        return axios.post(`/books/mark-as-taken/${id}`);
    }

}

export default EShopService;