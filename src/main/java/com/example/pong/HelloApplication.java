//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.pong;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class HelloApplication extends Application {

    static int stageHeight = 1000;
    static int stageWidth = 1500;

    static int recHeight = 200;
    static int recWidth = 50;
    static int rec1PosX = 50;
    static int rec1PosY = 500;
    static int rec2PosX = 1400;
    static int rec2PosY = 500;

    static int ballSize = 20;
    static int ball1PosX = 750;
    static int ballPosY = 500;

    static int directionBallX = 1;
    static int directionBallY = 1;

    Timeline tl;

    Rectangle ball;
    Rectangle r1;
    Rectangle r2;

    TextArea t1;

    Text score1, score2;
    int scoreInt1 = 0, scoreInt2 = 0;
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Pong");
        ball = new Rectangle(ball1PosX,ballPosY, ballSize, ballSize);

        r1 = new Rectangle(rec1PosX, rec1PosY, recWidth, recHeight);
        r2 = new Rectangle(rec2PosX, rec2PosY, recWidth, recHeight);
        t1 = new TextArea("text?");
        score1 = new Text(100,100, String.valueOf(scoreInt1));
        score2 = new Text(600,100, String.valueOf(scoreInt2));
        score1.setStyle("-fx-font-size: 100");
        score2.setStyle("-fx-font-size: 100");


        Group group = new Group(ball, r1, r2, score1, score2);
        primaryStage.setScene(new Scene(group, stageWidth, stageHeight));

        // tl = new Timeline();
        tl = new Timeline(new KeyFrame(Duration.millis(100), e -> run()));
        tl.setCycleCount(Timeline.INDEFINITE);

        //move R1 by mouse event
//        primaryStage.


        primaryStage.show();
        tl.play();
    }

    public void run(){



        if(((ball.getX() + ball.getWidth() +1 >= r2.getX())
                && (ball.getY()>=r2.getY() && ball.getY()<=(r2.getY()+r2.getHeight())))
                ||
                ((ball.getX()-1 <= r1.getX() + r1.getWidth())
                        && (ball.getY()>=r1.getY() && ball.getY()<=(r1.getY()+r1.getHeight()))
                )
        ) {
            directionBallX = -directionBallX;
        }

        if(ball.getY()<=0 || ball.getY()+ball.getWidth()>=stageHeight){
            directionBallY = -directionBallY;
        }

        if(ball.getX()<=0 || ball.getX()+ball.getWidth()>=stageWidth){
            if(ball.getX()<=0){
                scoreInt2 = ++scoreInt2;
                score2.setText(String.valueOf(scoreInt2));
            }

        }

        ball.setX(ball.getX()+ball.getWidth() * directionBallX) ;
        ball.setY(ball.getY()+ball.getHeight() * directionBallY);
        r2.setY(ball.getY());
        r1.setY(ball.getY());

        tl.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(1000),
                        new KeyValue(ball.translateXProperty(), ball.getX() + ball.getWidth()),
                        new KeyValue(ball.translateYProperty(), ball.getY() + ball.getWidth()),
                        new KeyValue(r2.translateYProperty(), ball.getY()),
                        new KeyValue(r1.translateYProperty(), ball.getY())

                )
        );
        tl.play();
    }


    public static void main(String[] args) {
        launch(args);
    }
}