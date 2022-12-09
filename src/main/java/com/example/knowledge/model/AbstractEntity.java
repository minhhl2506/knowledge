package com.example.knowledge.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.miscellaneous.TrimFilterFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.hibernate.annotations.TypeDef;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.NormalizerDef;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.jasypt.hibernate5.type.EncryptedStringType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.knowledge.cache.util.Constants;

import lombok.Data;

@AnalyzerDef(name = Constants.AnalyzerDefName.EDGE_NGRAM, tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = ASCIIFoldingFilterFactory.class),
		@TokenFilterDef(factory = LowerCaseFilterFactory.class), @TokenFilterDef(factory = TrimFilterFactory.class),
		@TokenFilterDef(factory = EdgeNGramFilterFactory.class, // Generate prefix tokens
				params = { @Parameter(name = Constants.AnalyzerDefName.MIN_GRAM_SIZE, value = "2"),
						@Parameter(name = Constants.AnalyzerDefName.MAX_GRAM_SIZE, value = "15") }) })
@AnalyzerDef(name = Constants.AnalyzerDefName.EDGE_NGRAM_QUERY, tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = ASCIIFoldingFilterFactory.class), // Replace accented characeters by their simpler
		// counterpart (è => e, etc.)
		@TokenFilterDef(factory = LowerCaseFilterFactory.class) // Lowercase all characters
})

@NormalizerDef(name = Constants.AnalyzerDefName.LOWERCASE, filters = {
		@TokenFilterDef(factory = ASCIIFoldingFilterFactory.class),
		@TokenFilterDef(factory = LowerCaseFilterFactory.class), @TokenFilterDef(factory = TrimFilterFactory.class) })

//dinh nghia encryptor cua jasypt
@TypeDef(name = "encryptedString", typeClass = EncryptedStringType.class, 
		parameters = {
		@org.hibernate.annotations.Parameter
		(name = "encryptorRegisteredName", value = "hibernateEncryptor") })

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4737229646610526219L;

	@CreatedBy
	@Column(name = "created_by", length = 255)
	private String createdBy;

	@CreatedDate
	@Column(name = "created_date", updatable = false)
	private Instant createdDate = Instant.now();

	@LastModifiedBy
	@Column(name = "last_modified_by", length = 255)
	private String lastModifiedBy;

	@LastModifiedDate
	@Column(name = "last_modified_date")
	private Instant lastModifiedDate = Instant.now();
}
