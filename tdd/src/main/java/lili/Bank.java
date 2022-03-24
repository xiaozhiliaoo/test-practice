package lili;

import java.util.Hashtable;

public class Bank  implements Expression {

    private Hashtable rates = new Hashtable();

    private class Pair {
        private String from;
        private String to;

        public Pair(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public boolean equals(Object obj) {
            Pair pair = (Pair) obj;
            return from.equals(pair.from) && to.equals(pair.to);
        }
    }

    public Money reduce(Bank source, String to) {
        return source.reduce(this, to);
    }

    public Money reduce(Expression source, String to) {
        return source.reduce(this, to);
    }



    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    @Override
    public Expression times(int multiplier) {
        return null;
    }

    int rate(String from, String to) {
        if(from.equals(to)) {
            return 1;
        }
        Integer rate = ((Integer) rates.get(new Pair(from, to)));
        return rate.intValue();
    }

    void addRate(String from, String to, int rate) {
        rates.put(new Pair(from, to), rate);
    }
}
