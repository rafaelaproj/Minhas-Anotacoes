package rafaelacs.com.br.exminhasanotacoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import rafaelacs.com.br.exminhasanotacoes.database.BancoDeDados;
/* FUNCIONA OK O APP - GRAVA, EDITA, DELETA, VOLTA TELA, VÊ O QUE JÁ FOI GRAVADO */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BancoDeDados bancoDeDados = new BancoDeDados(getBaseContext());
        final Cursor cursor = bancoDeDados.getNotes();

        String[] nomeCampos = new String[]{"_id", "titulo"};
        int[] idViews = new int[] {R.id.labelId, R.id.labelTitulo};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.modelo_lista, cursor, nomeCampos, idViews, 0);

        ListView lista = (ListView) findViewById(R.id.notesListView);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                cursor.moveToPosition(position);
                Intent intent = new Intent(MainActivity.this, EditNotesActivity.class);
                intent.putExtra("id", cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
                startActivity(intent);
                finish();
            }
        });
    }

    public void openScreenCreateNewNote(View v){
        Intent startNewActivity = new Intent(this, CreateNotesActivity.class);
        startActivity(startNewActivity);
    }

}
