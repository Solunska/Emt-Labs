import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import {Navigate} from 'react-router-dom';
import Countries from "../Countries/countries";
import EShopService from "../../repository/eshopRepository";
import Categories from "../Categories/categories";
import Books from "../Books/BookList/books";
import Authors from "../Authors/authors";
import Header from "../Header/header";
import BookEdit from "../Books/BookEdit/bookEdit";
import BookAdd from "../Books/BookAdd/bookAdd";
class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            countries: [],
            books: [],
            categories: [],
            authors: [],
            selectedBook: {}
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="container">
                        <Routes>
                            <Route path={"/countries"} element={<Countries countries={this.state.countries}/>}/>
                            <Route path={"/categories"} element={<Categories categories={this.state.categories}/>}/>
                            <Route path={"/books/add"} element={<BookAdd categories={this.state.categories}
                                                                         authors={this.state.authors}
                                                                         onAddBook={this.addBook}/>}/>
                            <Route path={"/books/edit/:id"} element={<BookEdit categories={this.state.categories}
                                                                               authors={this.state.authors}
                                                                               onEditBook={this.editBook}
                                                                               book={this.state.selectedBook}/>}/>
                            <Route path={"/books/mark-as-taken/:id"} element={<Books books={this.state.books}
                                                                   onDelete={this.deleteBook}
                                                                   onEdit={this.getBook}
                                                                   onMarkAsTaken={this.markAsTaken}/>}/>
                            <Route path={"/books"} element={<Books books={this.state.books}
                                                                   onDelete={this.deleteBook}
                                                                   onEdit={this.getBook}/>}/>
                            <Route path={"/authors"} element={<Authors authors={this.state.authors}/>}/>
                            <Route path={"*"} element={<Navigate to="/books"/>}/>
                        </Routes>
                    </div>
                </main>
            </Router>
        );
    }

    componentDidMount() {
        this.loadCountries();
        this.loadCategories();
        this.loadBooks();
        this.loadAuthors();
    }

    loadCountries = () => {
        EShopService.fetchCountries()
            .then((data) => {
                this.setState({
                    countries: data.data
                })
            });
    }

    loadCategories = () => {
        EShopService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }

    loadBooks = () => {
        EShopService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    loadAuthors = () => {
        EShopService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }
    deleteBook = (id) => {
        EShopService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    addBook = (name, category, author, availableCopies) => {
        EShopService.addBook(name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        EShopService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }

    editBook = (id, name, category, author, availableCopies) => {
        EShopService.editBook(id, name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }
    markAsTaken = (id) => {
        EShopService.markAsTaken(id)
            .then(() => {
                this.loadBooks();
            });
    }
}

export default App;