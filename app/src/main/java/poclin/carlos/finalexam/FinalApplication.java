package poclin.carlos.finalexam;


import android.app.Application;

import org.greenrobot.greendao.database.Database;

import poclin.carlos.finalexam.model.DaoMaster;
import poclin.carlos.finalexam.model.DaoSession;

public class FinalApplication extends Application{

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(this,"userdb");
        Database mydb = helper.getWritableDb();
        daoSession= new DaoMaster(mydb).newSession();
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }
}
