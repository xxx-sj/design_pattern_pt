package proxy.order;

// 연결을 관리하는 객체.
public class SimpleTransactionManager {
    private final SimpleConnection connection = new SimpleConnection();
    public void begin() {
        /**
         * 데이터 소스에서 커넥션 가져와서
         * setautocommit false 설정하고
         * thread local에 저장한다.
         */
        connection.begin();
    }

    /**
     * getConnection이라는 메서드는 threadlocal에서 가져와서 같은 트랜잭션을 반환한다.
     * 트랜잭션이 없으면 auto-commit 모드로 새 커넥션 빌려서 사용.
     */


    /**
     * thread local에서 connection 가져와서 commit 한다.
     */
    public void commit() {
        connection.commit();
    }

    /**
     * 동일하게 threadlocal에서 가져와서 rollback 호출.
     */
    public void rollback() {
        connection.rollback();
    }

    /**
     * cleanup
     * connection을 전달하면 autocommit 원복시키고
     * 커넥션 풀에 반환.
     * finally에서 thread local 정리.
     *
     */
}
