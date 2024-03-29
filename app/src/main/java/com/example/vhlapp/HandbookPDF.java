package com.example.vhlapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.widget.Toolbar;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.util.List;

public class HandbookPDF extends Activity implements OnPageChangeListener, OnLoadCompleteListener {
    private static final String TAG = HandbookPDF.class.getSimpleName();
    public static final String SAMPLE_FILE = "VHL_handbook.pdf";


    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handbook_p_d_f);

        Toolbar toolbar = ((Toolbar) findViewById(R.id.handbookToolbar));
        toolbar.setTitle("VHL Handbook");
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);

        Intent p = getIntent();
        int page = p.getIntExtra("pageNumber", 0);

        pdfView = (PDFView)findViewById(R.id.pdfView);
        pdfView.fromAsset(SAMPLE_FILE)
                .defaultPage(page-1) //because of 0-indexing
                .enableSwipe(true)
                .swipeHorizontal(true)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .pageSnap(true)
                .load();

    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }


    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

}