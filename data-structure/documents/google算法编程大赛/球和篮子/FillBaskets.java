public class FillBaskets {
    int count = 0;
    int baskets, capacity, balls;
    int[] bas;

    public int countWays(int baskets, int capacity, int balls) {
        if(baskets * capacity < balls)
            return 0;
        this.baskets = baskets;
        this.balls = balls;
        if(balls < capacity)
            capacity = balls;
        this.capacity = capacity;
        bas = new int[baskets];

        //�ӵ�0�����ӿ�ʼ��
        putBalls(0);
        return count;
    }

    //
    void putBalls(int n) {

        //����ǲ��ǵ����������
        if(n == baskets) {
            if(getSumBalls() == balls)
                count++;
            return;
        }

        //����n�����ӷ���,���Ҵ�0->capacity
        //һ����ѭ�ķ���
        for(int i = 0; i <= capacity; i++) {
            bas[n] = i;
            putBalls(n + 1);
        }
    }

    //�õ���ǰ�����������е���
    int getSumBalls() {
        int sum = 0;
        for(int i = 0; i < baskets; i++) { sum += bas[i]; }
        return sum;
    }
}
