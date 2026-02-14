package org.springexample;

import org.springexample.part1.Car1;

import org.springexample.part2.Car2;
import org.springexample.part2.Engine2;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
  //part 1
       Car1 c1 = new Car1();
       c1.startCar();
       //part 2
        //car banaya and engine manually car me dala yaha
        //aur manual chije ho gayi hai yaha
        //lose coupling nahi hai yaha
       // source code me changes karne pad rahe hai yaha
        Engine2 e2 = new Engine2();
        //thoda sa loose hai but pura naai kyu ki code me changes karne pd rahe hai yaha
        //yaha ek fayda hai bas ki kisi aur ka engine use nahi kar sakte hai yaha
        //tight coupling hai yaha
        Car2 c2 = new Car2(e2);
        e2.startEngine();
        c2.startCar();
    }
}
