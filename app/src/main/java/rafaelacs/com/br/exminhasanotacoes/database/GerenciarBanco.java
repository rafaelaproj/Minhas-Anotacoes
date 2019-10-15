package rafaelacs.com.br.exminhasanotacoes.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GerenciarBanco extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "database.db";
    public static final int VERSION = 1;


    public GerenciarBanco(Context context) {
        super(context, NOME_BANCO, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE anotacoes(" +
                "       _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "       titulo TEXT," +
                "       conteudo TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS anotacoes");
        onCreate(db);
    }


}
