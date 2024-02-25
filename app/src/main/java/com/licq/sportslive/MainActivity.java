package com.licq.sportslive;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableLayout = findViewById(R.id.tableLayout);

        new FetchStreamListTask().execute();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        new FetchStreamListTask().execute();
    }

    private class FetchStreamListTask extends AsyncTask<Void, Void, Matches> {
        @Override
        protected Matches doInBackground(Void... voids) {
            return WebScraper.scrape();
        }

        @Override
        protected void onPostExecute(Matches matches) {
            SportsTableAdapter adapter = new SportsTableAdapter(MainActivity.this, tableLayout, matches);
            adapter.createTable();
        }
    }

    private class SportsTableAdapter {
        private Context context;

        private TableLayout tableLayout;
        private Matches matches;

        public SportsTableAdapter(Context context, TableLayout tableLayout, Matches matches) {
            this.context = context;
            this.tableLayout = tableLayout;
            this.matches = matches;
        }

        public void createTable() {
            for (int i = 0; i < matches.getCount(); i++) {
                Match match = matches.get(i);
                TableRow tableRow = new TableRow(context);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
                tableRow.setLayoutParams(layoutParams);
                addMatchToTableRow(tableRow, match);

                tableRow.setOnFocusChangeListener((v, hasFocus) -> {
                    if (hasFocus) {
                        hightlightRow(v);
                    } else {
                        clearHighlight(v);
                    }
                });

                if (match.canWatch()) {
                    tableRow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, LiveStreamActivity.class);
                            intent.putExtra("links", match.getLinks());
                            context.startActivity(intent);
                        }
                    });
                }
                tableLayout.addView(tableRow);
            }
            if(tableLayout.getChildCount() > 1){
                tableLayout.getChildAt(1).requestFocus();
            }
        }

        private void addMatchToTableRow(TableRow tableRow, Match match) {
            createTextView(match.getCategory().toString(), tableRow);
            createTextView(match.getSubCategory().toString(), tableRow);
            createTextView(match.getTitle(), tableRow);
            createTextView(match.getStartTimeForDisplay(), tableRow);
            createTextView(match.getStatus().toString(), tableRow);
        }

        private void createTextView(String text, TableRow tableRow) {
            TextView textView = new TextView(context);
            textView.setText(text);
            textView.setPadding(5, 5, 5, 5);
            textView.setGravity(Gravity.CENTER);
            tableRow.addView(textView);
        }
    }

    private void hightlightRow(View view) {
        view.setBackgroundColor(Color.parseColor("#00EEEE"));
    }

    private void clearHighlight(View currentFocusedView) {
        currentFocusedView.setBackgroundColor(Color.TRANSPARENT);
    }
}
