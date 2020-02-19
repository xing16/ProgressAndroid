package com.xing.progressandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xing.progressandroid.R;
import com.xing.progressandroid.db.MySQLiteDatabaseOpenHelper;
import com.xing.progressandroid.db.User;
import com.xing.progressandroid.db.dao.DaoMaster;
import com.xing.progressandroid.db.dao.DaoSession;
import com.xing.progressandroid.db.dao.UserDao;

import java.util.List;

public class DbActivity extends AppCompatActivity {

    private UserDao userDao;
    private EditText nameEditText;
    private EditText userIdEditText;
    private EditText ageEditText;
    private EditText isDeveloperEditText;
    private EditText salaryEditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        userIdEditText = findViewById(R.id.et_user_id);
        nameEditText = findViewById(R.id.et_name);
        ageEditText = findViewById(R.id.et_age);
        isDeveloperEditText = findViewById(R.id.et_is_developer);
        salaryEditText = findViewById(R.id.et_salary);
        resultTextView = findViewById(R.id.tv_result);

        MySQLiteDatabaseOpenHelper devOpenHelper = new MySQLiteDatabaseOpenHelper(this, "db-user", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        int schemaVersion = daoMaster.getSchemaVersion();
        Log.e("debugdebug", "onCreate: " + schemaVersion);
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();


    }

    public void click(View v) {
        String userId = userIdEditText.getText().toString().trim();
        String name = nameEditText.getText().toString().trim();
        int age = 0;
        try {
            age = Integer.parseInt(ageEditText.getText().toString().trim());
        } catch (Exception e) {
            Toast.makeText(this, "invalid age value", Toast.LENGTH_SHORT).show();
        }
        String isDeveloperText = isDeveloperEditText.getText().toString().trim();
        boolean isDeveloper = false;
        if ("0".equals(isDeveloperText)) {
            isDeveloper = true;
        } else {
            isDeveloper = false;
        }

        float salary = 0f;
        try {
            salary = Float.parseFloat(salaryEditText.getText().toString().trim());
        } catch (Exception e) {
            Toast.makeText(this, "invalid salary value", Toast.LENGTH_SHORT).show();
        }
        User user = new User(userId, name, age, isDeveloper, salary, "beijing");
        switch (v.getId()) {
            case R.id.btn_insert:
                userDao.insert(user);
//                userDao.save(user);
//                userDao.saveInTx(user);
//                userDao.insertOrReplace(user);
                break;
            case R.id.btn_delete:
                User cacheUser = userDao.queryBuilder().where(UserDao.Properties.Name.eq(name)).unique();
                userDao.delete(cacheUser);
                break;
            case R.id.btn_update:
                userDao.update(user);
                break;
            case R.id.btn_query:
                break;
        }
        queryAll();
    }

    private void queryAll() {
        List<User> users = userDao.loadAll();
        StringBuilder sb = new StringBuilder();
        for (User user : users) {
            sb.append(user).append("\r\n");
        }
        resultTextView.setText(sb.toString());
    }
}
