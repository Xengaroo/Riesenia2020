import java.util.stream.IntStream;

public class Temp {

    private int[] parties;
    private int value;
    private int[] opposition;

    public Temp(int[] parties, int value) {
        this.parties = parties;
        this.value = value;
        this.opposition = new int[this.parties.length];
    }

    private void loopThrough(int[] temp, int i) {
        if ( IntStream.of(temp).sum() <= this.value && IntStream.of(this.opposition).sum() <= IntStream.of(temp).sum() ) {
            this.opposition = temp;
            if ( IntStream.of(this.opposition).sum() == this.value ) {
                return;
            }
        }
        if ( i < parties.length ) {
            loopThrough(temp, i+1);
            if ( IntStream.of(temp).sum() < value ) {
            int[] temp2 = temp.clone();
            temp2[i] = parties[i];
            loopThrough( temp2, i+1);
        }
        }
    }

    public int compute() {
        loopThrough(new int[this.opposition.length], 0 );
        return IntStream.of(this.opposition).sum()+1;
    }

}
