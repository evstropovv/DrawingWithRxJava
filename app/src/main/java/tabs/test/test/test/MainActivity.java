package tabs.test.test.test;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Person> persons;
    RecyclerView recyclerView;
    RVAdapter rvAdapter;
    MyLayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeList();
        recyclerView = (RecyclerView)findViewById(R.id.rv);
        rvAdapter = new RVAdapter(persons);
        System.out.println(persons.size());

        layoutManager =new MyLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rvAdapter);

    }

    private void initializeList(){
        persons = new ArrayList<>();
        persons.add(new Person("Emma Wilson", "23 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Lavery Maiss", "25 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Lillie Watts", "35 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Lillie Wilson", "44 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Lillie Watts", "35 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Wilson Watts", "35 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Lillie Watts", "33 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Maiss Watts", "66 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Watts Lavery", "77 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Wilson Watts", "35 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Lillie Watts", "11 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Wilson Watts", "33 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Emma Wilson", "23 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Lavery Maiss", "25 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Lillie Watts", "35 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Lillie Wilson", "44 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Lillie Watts", "35 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Wilson Watts", "35 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Lillie Watts", "33 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Maiss Watts", "66 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Watts Lavery", "77 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Wilson Watts", "35 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Lillie Watts", "11 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Wilson Watts", "33 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Emma Wilson", "23 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Lavery Maiss", "25 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Lillie Watts", "35 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Lillie Wilson", "44 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Lillie Watts", "35 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Wilson Watts", "35 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Lillie Watts", "33 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Maiss Watts", "66 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Watts Lavery", "77 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Wilson Watts", "35 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Lillie Watts", "11 years old", R.mipmap.ic_launcher));
        persons.add(new Person("Wilson Watts", "33 years old", R.mipmap.ic_launcher));
    }
}
