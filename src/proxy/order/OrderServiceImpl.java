package proxy.order;

public class OrderServiceImpl implements OrderService {
    @Override
    public void placeOrder(String userId, String productId, int quantity) {
        System.out.println("[OrderServiceImpl] 주문 로직 실행 ??");
        // 1) 재고 차감
        // 2) 주문 내역 저장
        // 3) 포인트 적립
        // 중간에 예외 터지면 롤백해야 한다고 가정
    }
}
