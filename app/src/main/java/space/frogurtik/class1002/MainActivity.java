package space.frogurtik.class1002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    ListView bookList;
    Button button;
    EditText title_book, author_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookList = findViewById(R.id.book_list);
        button = findViewById(R.id.button_to_add_book);
        title_book = findViewById(R.id.title_book);
        author_book = findViewById(R.id.author_book);
        //TODO подготовка данных
        LinkedList<Book> bookLinkedList = new LinkedList<>();
        bookLinkedList.add(new Book("Гарри Поттер", "Роулинг"));
        bookLinkedList.add(new Book("Идиот", "Ф. Достоевкий"));
        bookLinkedList.add(new Book("Гиперболоид инженера Гарина",
                "А. Толстой"));
        bookLinkedList.add(new Book("Роковые яйца", "М. Булгаков"));
        bookLinkedList.add(new Book("Колобок", "народ"));
        //TODO создание адаптера
        ArrayAdapter<Book> adapter = new ArrayAdapter<>(this,
                R.layout.list_items, bookLinkedList);
        bookList.setAdapter(adapter);
        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"Книга \""+bookLinkedList.get(i).title+"\" автора "+bookLinkedList.get(i).author+" удалена из списка",
                        Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bookLinkedList.remove(i);
                adapter.notifyDataSetChanged();
            }
        });
        //TODO создать интерфейс добавления/удаления книги дз
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!title_book.getText().toString().equals("") && !author_book.getText().toString().equals("")){
                    String title_new_book = title_book.getText().toString();
                    String author_new_book = author_book.getText().toString();
                    bookLinkedList.add(new Book(title_new_book, author_new_book));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    adapter.notifyDataSetChanged();
                }else Toast.makeText(getApplicationContext(), "Поля \"Название книги\" и \"Автор книги\" пусты", Toast.LENGTH_SHORT).show();
            }
        });
    }
}