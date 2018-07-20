package duomi.mongodb.dao.annotation;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 查询类型的媒介类<br/>
 * 目前有支持三种类型：<br/>
 * 1. equals：相等 2. like:mongodb的like查询 3. in:用于列表的in类型查询
 * 
 */
public enum QueryType {
	EQUALS {
		@Override
		public Criteria buildCriteria(QueryField queryFieldAnnotation, Field field, Object value) {
			if (check(queryFieldAnnotation, field, value)) {
				String queryField = getQueryFieldName(queryFieldAnnotation, field);
				return Criteria.where(queryField).is(value.toString());
			}
			return new Criteria();
		}
	},
	LIKE {
		@Override
		public Criteria buildCriteria(QueryField queryFieldAnnotation, Field field, Object value) {
			if (check(queryFieldAnnotation, field, value)) {
				String queryField = getQueryFieldName(queryFieldAnnotation, field);
				return Criteria.where(queryField).regex(value.toString());
			}
			return new Criteria();
		}
	},
	IN {
		@Override
		public Criteria buildCriteria(QueryField queryFieldAnnotation, Field field, Object value) {
			if (check(queryFieldAnnotation, field, value)) {
				if (value instanceof List) {
					String queryField = getQueryFieldName(queryFieldAnnotation, field);
					// 此处必须转型为List，否则会在in外面多一层[]
					return Criteria.where(queryField).in((List<?>) value);
				}
			}
			return new Criteria();
		}
	};

	private static boolean check(QueryField queryField, Field field, Object value) {
		return !(queryField == null || field == null || value == null);
	}

	public abstract Criteria buildCriteria(QueryField queryFieldAnnotation, Field field, Object value);

	/**
	 * 如果实体bean的字段上QueryField注解没有设置attribute属性时，默认为该字段的名称
	 * 
	 * @param queryFieldAnnotation
	 * @param field
	 * @return
	 */
	private static String getQueryFieldName(QueryField queryField, Field field) {
		String queryFieldValue = queryField.attribute();
		if (!StringUtils.hasText(queryFieldValue)) {
			queryFieldValue = field.getName();
		}
		return queryFieldValue;
	}
}
