package ru.netology.manager;

import com.sun.jdi.event.StepEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Matches;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductMangerTest {
    ProductRepository repo = new ProductRepository(); //создал экземпляр репозитория
    ProductManger manger = new ProductManger(repo); //создал экземпляр менеджера и передал в параметры репозиторий

    @Test
    public void shouldSearchByText() { //тест на поиск
        Product matches = new Product(5, "Matches", 100); //создаю продукты
        Product smartphone = new Smartphone(13, "Iphone", 700, "Apple");
        Product book = new Book(25, "Bible", 1000, "God");
        repo.save(matches);//сохраняю их с помощью метода
        repo.save(smartphone);
        repo.save(book);

        String text = "Matches";
        Product[] expected = new Product[] {matches};
        Product[] actual = manger.searchBy(text);
        assertArrayEquals(expected, actual);
    }

}