package ru.netology.manager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductMangerTest {
    static ProductRepository repo = new ProductRepository(); //создал экземпляр репозитория
    ProductManger manger = new ProductManger(repo); //создал экземпляр менеджера и передал в параметры репозиторий

    private static final Product smartphone = new Smartphone(2, "Nokia-3310", 100, "Nokia");//создаю экземпляры товаров
    private static final Product smartphone1 = new Smartphone(5, "m65", 100500, "Siemens");
    private static final Product book = new Book(25, "Bible", 1000, "God");
    private static final Product book1 = new Book(1, "Три товарища", 50, "Эрих Мария Ремарк");
    private static final Product book2 = new Book(3, "Ремарк", 123, "Три товарища");

    @BeforeAll
    static void setUp() { //Настройки. Сохраняю с помощью методов продукты.
        repo.save(smartphone);
        repo.save(smartphone1);
        repo.save(book);
        repo.save(book1);
        repo.save(book2);
    }

    @Test
    public void shouldFindBookName() {
        Product[] expected = {book}; //Можно написать Product[]{book}; массив с элементом и в фигурные скобки помещаю сохраненный элемент.
        Product[] actual = manger.searchBy("Bible"); //Ожидаю текст, который будет проверять метод поиска.

        assertArrayEquals(expected, actual); //Сравниваю массивы.
    }

    @Test
    public void shouldFindBookAuthor() {
        Product[] expected = {book1};
        Product[] actual = manger.searchBy("Эрих Мария Ремарк");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSmartphoneProducer() {
        Product[] expected = {smartphone};
        Product[] actual = manger.searchBy("Nokia");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSmartphoneName() {
        Product[] expected = {smartphone1};
        Product[] actual = manger.searchBy("m65");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTheSameText() {
        Product[] expected = {book1, book2};
        Product[] actual = manger.searchBy("Три товарища");

        assertArrayEquals(expected, actual);
    }

}

