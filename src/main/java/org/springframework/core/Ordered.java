package org.springframework.core;

/**
 * Interface that can be implemented by objects that should be
 * orderable, e.g. in a Collection. The actual order can be
 * interpreted as prioritization, the first object (with the
 * lowest order value) having the highest priority.
 *
 * @author Juergen Hoeller
 * @since 07.04.2003
 *
 * 순서가능한 오프젝트가 구현해야하는 인터페이스 예를 들자면 콜렉션 같은
 * 실제적 순서는 우선권에 의해 해석된다
 */
public interface Ordered {

  /**
   * Return the order value of this object,
   * higher value meaning greater in terms of sorting.
   * Normally starting with 0 or 1, Integer.MAX_VALUE
   * indicating greatest.
   * Same order values will result in arbitrary positions
   * for the affected objects.
   *
   * <p>Higher value can be interpreted as lower priority,
   * consequently the first object has highest priority
   * (somewhat analogous to Servlet "load-on-startup" values).
   *
   * @return the order value
   */
	public int getOrder();
}
