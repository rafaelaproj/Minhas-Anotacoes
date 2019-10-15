package rafaelacs.com.br.exminhasanotacoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import rafaelacs.com.br.exminhasanotacoes.database.BancoDeDados;

public class EditNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        BancoDeDados bancoDeDados = new BancoDeDados(getBaseContext());
        final Cursor cursor = bancoDeDados.getNotesById(this.getIntent().getIntExtra("id", 0));

        EditText titulo = (EditText)findViewById(R.id.tituloEditText);
        EditText conteudo = (EditText)findViewById(R.id.conteudoEditText);

        titulo.setText(cursor.getString(cursor.getColumnIndexOrThrow("titulo")));
        conteudo.setText(cursor.getString(cursor.getColumnIndexOrThrow("conteudo")));
    }

    public void back(View v) {
        Intent startNewActivity = new Intent(this, MainActivity.class);
        startActivity(startNewActivity);
    }

    public void updateNotes(View v) {
        BancoDeDados bancoDeDados = new BancoDeDados(getBaseContext());
        EditText titulo = (EditText) findViewById(R.id.tituloEditText);
        EditText conteudo = (EditText) findViewById(R.id.conteudoEditText);

        try {
            bancoDeDados.updateNotes(this.getIntent().getIntExtra("id", 0), titulo.getText().toString(), conteudo.getText().toString());
            Toast.makeText(getApplicationContext(), "Anotação atualizada com sucesso!", Toast.LENGTH_LONG).show();
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "Não foi possível atualizar a anotação. Tente novamente.", Toast.LENGTH_LONG).show();
        }

        back(v);
    }

    public void deleteNotes(View v) {
        BancoDeDados bancoDeDados = new BancoDeDados(getBaseContext());
        EditText titulo = (EditText) findViewById(R.id.tituloEditText);
        EditText conteudo = (EditText) findViewById(R.id.conteudoEditText);

        try {
            bancoDeDados.deleteNotes(this.getIntent().getIntExtra("id", 0));
            Toast.makeText(getApplicationContext(), "Anotação excluída com sucesso!", Toast.LENGTH_LONG).show();
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "Não foi possível excluir a anotação. Tente novamente.", Toast.LENGTH_LONG).show();
        }

        back(v);
    }

}
