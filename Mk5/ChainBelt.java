package EnemyLevelScaling.GeneticProgrammer.Mk5;

import java.util.Iterator;

public class ChainBelt<T> implements Iterable<T>
{
	private class Node<U>
	{
		private U Cargo;
		private Node<U> NextNode;
		
		public Node(U in)
		{
			this.Cargo = in;
		}
	}
	
	private Node<T> Head;
	private Node<T> CurrNode;
	private int size;
	
	public ChainBelt()
	{
		this.Head = null;
		this.CurrNode = null;
		this.size = 0;
	}
	
	public void add(T in)
	{
		if(this.Head == null)
		{
			this.Head = new Node<T>(in);
			this.CurrNode = this.Head;
		}
		else
		{
			this.CurrNode.NextNode = new Node<T>(in);
			this.CurrNode = this.CurrNode.NextNode;
		}
		
		this.size++;
	}
	
	public T pop()
	{
		T result = this.Head.Cargo;
		
		if(this.size > 1)
		{
			this.Head = this.Head.NextNode;
		}
		else
		{
			this.Head = null;
			this.CurrNode = null;
		}
		
		this.size--;
		return result;
	}
	
	public int size()
	{
		return this.size;
	}
	
	public boolean isEmpty()
	{
		return this.size == 0;
	}
	
	public T get(int i)
	{
		Node<T> LocCurrNode = this.Head;
		int index = 0;
		
		while(index < i)
		{
			LocCurrNode = LocCurrNode.NextNode;
			index++;
		}
		
		return LocCurrNode.Cargo;
	}

	@Override
	public Iterator<T> iterator()
	{
		return new ChainBeltIterator<T>(this.Head);
	}
	
	public class ChainBeltIterator<V> implements Iterator<V>
	{
		private Node<V> CurrNode;
		
		public ChainBeltIterator(Node<V> Head)
		{
			this.CurrNode = new Node<V>(null);
			this.CurrNode.NextNode = Head;
		}
		
		@Override
		public boolean hasNext()
		{
			return this.CurrNode.NextNode != null;
		}

		@Override
		public V next()
		{
			this.CurrNode = this.CurrNode.NextNode;
			return this.CurrNode.Cargo;
		}
	}
}
