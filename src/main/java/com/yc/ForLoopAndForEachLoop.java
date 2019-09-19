package com.yc;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class ForLoopAndForEachLoop {

    enum Suit { CLUB, DIAMOND, HEART, SPADE }
    enum Rank { ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT,
        NINE, TEN, JACK, QUEEN, KING }

    Collection<Suit> suits = Arrays.asList(Suit.values());

    Collection<Rank> ranks = Arrays.asList(Rank.values());

    ArrayList<Integer> list1 = new ArrayList<>();
    ArrayList<Integer> list2 = new ArrayList<>();

    @Before
    public void init(){
        list1.add(1);
        list1.add(2);
        list1.add(3);

        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(1);
        list2.add(2);
        list2.add(3);

    }

    @Test
    public void testInteger(){
        Comparator<Integer> natureOrder = (i, j) -> (i < j) ? -1 : (i == j) ? 0 : 1;
        Integer i1 = new Integer(42);
        Integer i2 = new Integer(42);
        int compare = natureOrder.compare(i1, i2);
        System.out.println(compare);
        System.out.println(i1 == i2);
    }


    @Test
    public void testFor(){
        ArrayList<Card> list3 = new ArrayList<>();

       /*for(Iterator<Suit> i = suits.iterator(); i.hasNext();){
            for(Iterator<Rank> j = ranks.iterator(); j.hasNext();){
                //System.out.println(i.next()+"--->"+j.next());
                list3.add(new Card(i.next(), j.next()));
            }
        }*/

        /*for(Iterator<Integer> i = list1.iterator(); i.hasNext();){
            for(Iterator<Integer> j = list2.iterator(); j.hasNext();){
                System.out.println(i.next()+"--->"+j.next());
            }
        }*/

        for(Suit suit : suits){
            for(Rank rank : ranks){
                list3.add(new Card(suit, rank));
            }
        }

        System.out.println(list3);
    }
    class Card{
        private Suit suit;

        private Rank rank;

        public Card(Suit suit, Rank rank) {
            this.suit = suit;
            this.rank = rank;
        }

        public Suit getSuit() {
            return suit;
        }

        public void setSuit(Suit suit) {
            this.suit = suit;
        }

        public Rank getRank() {
            return rank;
        }

        public void setRank(Rank rank) {
            this.rank = rank;
        }

        @Override
        public String toString() {
            return "Card{" +
                    "suit=" + suit +
                    ", rank=" + rank +
                    '}';
        }
    }
}

