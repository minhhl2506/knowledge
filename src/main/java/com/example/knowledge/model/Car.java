package com.example.knowledge.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Normalizer;
import org.hibernate.search.annotations.SortableField;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TermVector;

import com.example.knowledge.cache.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "_car")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Indexed
public class Car extends AbstractEntity implements Serializable {

	/** The Constant serialVersionUID */
	private static final long serialVersionUID = 3752466100126131064L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Field(index = Index.YES, termVector = TermVector.YES, analyze = Analyze.YES,
			analyzer = @Analyzer(definition = Constants.AnalyzerDefName.EDGE_NGRAM),
			store = Store.YES)
	@Field(name = SortName.NAME_SORT, termVector = TermVector.YES, index = Index.YES, 
		analyze = Analyze.NO, 
		normalizer = @Normalizer(definition = Constants.AnalyzerDefName.LOWERCASE), 
		store = Store.YES)
	//index.yes thể hiện trường name sẽ được đ
	//edgeNGram là tokenizer hữu ích trong việc nhập và trả về kết quả ngay lập tức
	//store.yes phục vụ cho việc projection
	@Column(name = "name", length = 255)
	@Type(type = "encryptedString")
	private String name;

	@Field
	@Column(name = "price", length = 255)
	private int price;
	
	@Field
	@Column(name = "status", nullable = false, length = 1)
	private int status;
	
	@Field(termVector = TermVector.YES, index = Index.YES, analyze = Analyze.YES, store = Store.YES)
	@Field(name = "lastModifiedDate", index = Index.YES, analyze = Analyze.NO, store = Store.YES)
    @SortableField(forField = "lastModifiedDate")
    @Column(name = "LAST_MODIFIED_DATE", updatable = false, insertable = false)
    private LocalDateTime lastModifiedDateTime;

	@Column(name = "user_id", nullable = true)
	private long userId;
	
	public interface FieldName {
		String NAME = "name";
		
		String PRICE = "price";

		String STATUS = "status";
	}
	
	public interface SortName {
		String NAME_SORT = "nameSort";
	}
}
