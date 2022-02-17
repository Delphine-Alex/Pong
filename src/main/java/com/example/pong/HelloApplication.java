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
import javafx.stage.Stage;
import javafx.util.Duration;

public class HelloApplication extends Application {

    static int circleSize = 50;


    static int stageWidth = 1280;
    static int stageHeight = 720;

    static int recHeight = 25;
    static int recWidth = 150;

    static int rec1posX = 1;
    static int rec1posY = 50;

    static int rec2posX = 1254;
    static int rec2posY = 325;


    static int ballSize = 25;
    static int ballPosX = 640;
    static int ballPosY = 325;

    static int directionBallX = 1;
    static int directionBallY = 1;

    Timeline tl;
    Rectangle ball;
    Rectangle r2;
    Rectangle r1;

    TextArea txt;

    @Override
    public void start(Stage stage) throws IOException {

        ball = new Rectangle(ballPosX, ballPosY, ballSize, ballSize);
        r1 = new Rectangle(rec1posX, rec1posY, recHeight, recWidth);
        r2 = new Rectangle(rec2posX, rec2posY, recHeight, recWidth);
        txt = new TextArea(("text?");
        txt.fontProperty()

        Group group = new Group(ball, r1, r2);
        Scene scene = new Scene(group, stageWidth, stageHeight);

        stage.setTitle("Pong!");
        stage.setScene(scene);

        //tl = new Timeline();
        tl = new Timeline(new KeyFrame(Duration.millis(100), e -> run()));
        tl.setCycleCount(Timeline.INDEFINITE);
        stage.show();

        // Move R1 by mouse event

        tl.play();
    }

    public void run() {

        if((ball.getX() + ball.getWidth() + 1 >= r2.getX()) && (ball.getY()>=r2.getY() && ball.getY()<=(r2.getY()+r2.getHeight()))
                || (ball.getX()-1 <= r1.getX() + r1.getWidth()) && (ball.getY()>=r1.getY() && ball.getY()<=(r1.getY()+r1.getHeight())))
        {
            directionBallX=-directionBallX;
        }

        if(ball.getY()<=0 || ball.getY()+ ball.getWidth()>=stageHeight){
            directionBallY = -directionBallY;
        }

        ball.setX(ball.getX()+ball.getWidth() * directionBallX);
        ball.setY(ball.getY()+ball.getHeight()/8 * directionBallY);
        r2.setY(ball.getX());
        r1.setY(ball.getY());

        tl.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(5000),
                        new KeyValue(ball.translateXProperty(), ball.getX() + ball.getWidth()),
                        new KeyValue(ball.translateYProperty(), ball.getY() + ball.getWidth()),
                        new KeyValue(r2.translateYProperty(), ball.getY()),
                        new KeyValue(r1.translateYProperty(), ball.getY())

                )

        );

        tl.play();
    }

    public static void main(String[] args) {
        launch();
    }
}