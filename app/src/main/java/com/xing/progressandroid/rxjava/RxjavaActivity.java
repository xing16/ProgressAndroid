package com.xing.progressandroid.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.xing.progressandroid.R;
import com.xing.progressandroid.rxjava.bean.Person;
import com.xing.progressandroid.rxjava.bean.Plan;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RxjavaActivity extends AppCompatActivity {

    private static final String TAG = "RxjavaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        // 打印 List<Person> 中每个 person 的每条 plan 的每条 action
        List<Person> persons = new ArrayList<>();

        // tom
        List<Plan> tomPlans = new ArrayList<>();
        List<String> tomActions = new ArrayList<>();
        tomActions.add("Android");
        tomActions.add("iOS");
        tomActions.add("Flutter");
        tomPlans.add(new Plan("code", System.currentTimeMillis(), tomActions));

        List<Plan> jackPlans = new ArrayList<>();
        List<String> jackActions = new ArrayList<>();
        tomActions.add("html5");
        tomActions.add("css");
        tomActions.add("vue.js");
        tomPlans.add(new Plan("web", System.currentTimeMillis(), jackActions));


        persons.add(new Person("tom", tomPlans));
        persons.add(new Person("jack", jackPlans));

        Observable.fromIterable(persons)
                .flatMap(new Function<Person, ObservableSource<Plan>>() {
                    @Override
                    public ObservableSource<Plan> apply(Person person) throws Exception {
                        return Observable.fromIterable(person.getPlanList());
                    }
                }).flatMap(new Function<Plan, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Plan plan) throws Exception {
                return Observable.fromIterable(plan.getActionList());
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, "accept: " + s);
            }
        });


    }
}
