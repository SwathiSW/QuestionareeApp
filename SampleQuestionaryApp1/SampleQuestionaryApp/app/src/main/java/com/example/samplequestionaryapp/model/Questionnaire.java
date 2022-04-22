package com.example.samplequestionaryapp.model;

import java.util.List;

public class Questionnaire{
	private String paperName;
	private int marksEachQues;
	private List<QuestionsItem> questions;
	private int paperId;

	public String getPaperName(){
		return paperName;
	}

	public int getMarksEachQues(){
		return marksEachQues;
	}

	public List<QuestionsItem> getQuestions(){
		return questions;
	}

	public int getPaperId(){
		return paperId;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public void setMarksEachQues(int marksEachQues) {
		this.marksEachQues = marksEachQues;
	}

	public void setQuestions(List<QuestionsItem> questions) {
		this.questions = questions;
	}

	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}

	public static class QuestionsItem{
		private List<OptionsItem> options;
		private int quesType;
		private int questionId;
		private String question;
		private List<Integer> correctOption;
		private int userOpt;
		private List<Integer> userOptCheck;

		public List<OptionsItem> getOptions(){
			return options;
		}

		public int getQuesType(){
			return quesType;
		}

		public int getQuestionId(){
			return questionId;
		}

		public String getQuestion(){
			return question;
		}

		public List<Integer> getCorrectOption(){
			return correctOption;
		}

		public void setOptions(List<OptionsItem> options) {
			this.options = options;
		}

		public void setQuesType(int quesType) {
			this.quesType = quesType;
		}

		public void setQuestionId(int questionId) {
			this.questionId = questionId;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public void setCorrectOption(List<Integer> correctOption) {
			this.correctOption = correctOption;
		}

		public int getUserOpt() {
			return userOpt;
		}

		public void setUserOpt(int userOpt) {
			this.userOpt = userOpt;
		}

		public List<Integer> getUserOptCheck() {
			return userOptCheck;
		}

		public void setUserOptCheck(List<Integer> userOptCheck) {
			this.userOptCheck = userOptCheck;
		}

		public class OptionsItem{
			private int optionId;
			private String option;
			private int isChecked;

			public int getOptionId(){
				return optionId;
			}

			public String getOption(){
				return option;
			}

			public void setOptionId(int optionId) {
				this.optionId = optionId;
			}

			public void setOption(String option) {
				this.option = option;
			}

			public int getIsChecked() {
				return isChecked;
			}

			public void setIsChecked(int isChecked) {
				this.isChecked = isChecked;
			}
		}
	}
}