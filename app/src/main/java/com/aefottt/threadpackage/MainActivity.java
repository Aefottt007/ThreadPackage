package com.aefottt.threadpackage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ProgressBar pbOne, pbTwo, pbThree, pbFour, pbFive;
    private Button btnOne, btnTwo, btnThree, btnFour, btnFive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pbOne = findViewById(R.id.pb_download_one);
        pbTwo = findViewById(R.id.pb_download_two);
        pbThree = findViewById(R.id.pb_download_three);
        pbFour = findViewById(R.id.pb_download_four);
        pbFive = findViewById(R.id.pb_download_five);
        btnOne = findViewById(R.id.btn_download_one);
        btnTwo = findViewById(R.id.btn_download_two);
        btnThree = findViewById(R.id.btn_download_three);
        btnFour = findViewById(R.id.btn_download_four);
        btnFive = findViewById(R.id.btn_download_five);
        btnOne.setOnClickListener(view -> {
            if (!isOneDownload && one < 100 && !isSuccessOne){
                isOneDownload = true;
                ThreadPoolProxyFactory.getDownLoadThreadPoolProxy().execute(downLoadOneRunnable);
                if (one == 0){
                    btnOne.setText("等待中");
                }
            }else if (isOneDownload && one >= 0 && !isSuccessOne){
                isOneDownload = false;
                ThreadPoolProxyFactory.getDownLoadThreadPoolProxy().remove(downLoadOneRunnable);
                btnOne.setText("暂停");
            }else if (isSuccessOne){
                isSuccessOne = false;
                one = 0;
                btnOne.setText("下载");
                isOneDownload = false;
                pbOne.setProgress(0);
            }
        });
        btnTwo.setOnClickListener(view -> {
            if (!isTwoDownload && two < 100 && !isSuccessTwo){
                isTwoDownload = true;
                ThreadPoolProxyFactory.getDownLoadThreadPoolProxy().execute(downLoadTwoRunnable);
                if (two == 0){
                    btnTwo.setText("等待中");
                }
            }else if (isTwoDownload && two >= 0 && !isSuccessTwo){
                isTwoDownload = false;
                ThreadPoolProxyFactory.getDownLoadThreadPoolProxy().remove(downLoadTwoRunnable);
                btnTwo.setText("暂停");
            }else if (isSuccessTwo){
                isSuccessTwo = false;
                two = 0;
                btnTwo.setText("下载");
                isTwoDownload = false;
                pbTwo.setProgress(0);
            }
        });
        btnThree.setOnClickListener(view -> {
            if (!isThreeDownload && three < 100 && !isSuccessThree){
                isThreeDownload = true;
                ThreadPoolProxyFactory.getDownLoadThreadPoolProxy().execute(downLoadThreeRunnable);
                if (three == 0){
                    btnThree.setText("等待中");
                }
            }else if (isThreeDownload && three >= 0 && !isSuccessThree){
                isThreeDownload = false;
                ThreadPoolProxyFactory.getDownLoadThreadPoolProxy().remove(downLoadThreeRunnable);
                btnThree.setText("暂停");
            }else if (isSuccessThree){
                isSuccessThree = false;
                three = 0;
                btnThree.setText("下载");
                isThreeDownload = false;
                pbThree.setProgress(0);
            }
        });
        btnFour.setOnClickListener(view -> {
            if (!isFourDownload && four < 100 && !isSuccessFour){
                isFourDownload = true;
                ThreadPoolProxyFactory.getDownLoadThreadPoolProxy().execute(downLoadFourRunnable);
                if (four == 0){
                    btnFour.setText("等待中");
                }
            }else if (isFourDownload && four >= 0 && !isSuccessFour){
                isFourDownload = false;
                ThreadPoolProxyFactory.getDownLoadThreadPoolProxy().remove(downLoadFourRunnable);
                btnFour.setText("暂停");
            }else if (isSuccessFour){
                isSuccessFour = false;
                four = 0;
                btnFour.setText("下载");
                isFourDownload = false;
                pbFour.setProgress(0);
            }
        });
        btnFive.setOnClickListener(view -> {
            if (!isFiveDownload && five < 100 && !isSuccessFive){
                isFiveDownload = true;
                ThreadPoolProxyFactory.getDownLoadThreadPoolProxy().execute(downLoadFiveRunnable);
                if (five == 0){
                    btnFive.setText("等待中");
                }
            }else if (isFiveDownload && five >= 0 && !isSuccessFive){
                isFiveDownload = false;
                ThreadPoolProxyFactory.getDownLoadThreadPoolProxy().remove(downLoadFiveRunnable);
                btnFive.setText("暂停");
            }else if (isSuccessFive){
                isSuccessFive = false;
                five = 0;
                btnFive.setText("下载");
                isFiveDownload = false;
                pbFive.setProgress(0);
            }
        });

    }

    private int one, two, three, four, five;
    private boolean isOneDownload, isTwoDownload, isThreeDownload, isFourDownload, isFiveDownload;
    private boolean isSuccessOne, isSuccessTwo, isSuccessThree, isSuccessFour, isSuccessFive;
    private final Runnable downLoadOneRunnable = () -> {
        while (one < 100 && isOneDownload){
            one += (int) (Math.random() * 20);
            pbOne.setProgress(Math.min(one, 100));
            btnOne.setText("下载中");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        btnOne.setText("已完成");
        isSuccessOne = true;
    };
    private final Runnable downLoadTwoRunnable = () -> {
        while (two < 100 && isTwoDownload){
            two += (int) (Math.random() * 20);
            pbTwo.setProgress(Math.min(two, 100));
            btnTwo.setText("下载中");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        btnTwo.setText("已完成");
        isSuccessTwo = true;
    };
    private final Runnable downLoadThreeRunnable = () -> {
        while (three < 100 && isThreeDownload){
            three += (int) (Math.random() * 20);
            pbThree.setProgress(Math.min(three, 100));
            btnThree.setText("下载中");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        btnThree.setText("已完成");
        isSuccessThree = true;
    };
    private final Runnable downLoadFourRunnable = () -> {
        while (four < 100 && isFourDownload){
            four += (int) (Math.random() * 20);
            pbFour.setProgress(Math.min(four, 100));
            btnFour.setText("下载中");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        btnFour.setText("已完成");
        isSuccessFour = true;
    };
    private final Runnable downLoadFiveRunnable = () -> {
        while (five < 100 && isFiveDownload){
            five += (int) (Math.random() * 20);
            pbFive.setProgress(Math.min(five, 100));
            btnFive.setText("下载中");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        btnFive.setText("已完成");
        isSuccessFive = true;
    };
}