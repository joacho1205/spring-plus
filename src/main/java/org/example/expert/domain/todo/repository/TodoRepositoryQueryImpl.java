package org.example.expert.domain.todo.repository;

import java.util.Optional;

import org.example.expert.domain.todo.entity.QTodo;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.entity.QUser;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

public class TodoRepositoryQueryImpl implements TodoRepositoryQuery {
	private final JPAQueryFactory queryFactory;

	public TodoRepositoryQueryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public Optional<Todo> findByIdWithUser(Long todoId) {
		Todo todo = queryFactory
			.selectFrom(QTodo.todo)
			.leftJoin(QTodo.todo.user, QUser.user)
			.fetchJoin()
			.where(QTodo.todo.id.eq(todoId))
			.fetchOne();

		return Optional.ofNullable(todo);
	}

}
