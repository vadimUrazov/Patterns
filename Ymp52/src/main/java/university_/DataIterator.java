package university_;

import java.util.Iterator;

public class DataIterator implements Iterator<Integer> {
    private int index;
    private Data setOfGroups;

    public DataIterator(Data setOfGroups) {
        index = -1;
        this.setOfGroups = setOfGroups;
    }

    @Override
    public boolean hasNext() {
        int size = 0;
        for (int i = 0; i < setOfGroups.length(); i++) {
            size += setOfGroups.getSet().get(i).length();
        }
        return (index+1) < size;
    }

    @Override
    public Integer next(){
        if(!hasNext()){

        }
        index++;
        int res, curentIndex=index, i=0;
        for(i=0; i<setOfGroups.length(); i++){
            if(curentIndex<setOfGroups.getSet().get(i).length()){
                break;
            }
            if(curentIndex>=setOfGroups.getSet().get(i).length()){
                curentIndex-=setOfGroups.getSet().get(i).length();
            }
        }
        return setOfGroups.getSet().get(i).getData().get(curentIndex);
    }
}
