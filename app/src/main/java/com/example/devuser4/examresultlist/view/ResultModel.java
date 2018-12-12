package com.example.devuser4.examresultlist.view;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResultModel implements Serializable {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class Data {

        @SerializedName("exams")
        @Expose
        private List<Exam> exams = null;
        @SerializedName("subjects")
        @Expose
        private List<Subject> subjects = null;
        @SerializedName("chapters")
        @Expose
        private List<Chapter> chapters = null;
        @SerializedName("questions")
        @Expose
        private List<Question> questions = null;

        public List<Exam> getExams() {
            return exams;
        }

        public void setExams(List<Exam> exams) {
            this.exams = exams;
        }

        public List<Subject> getSubjects() {
            return subjects;
        }

        public void setSubjects(List<Subject> subjects) {
            this.subjects = subjects;
        }

        public List<Chapter> getChapters() {
            return chapters;
        }

        public void setChapters(List<Chapter> chapters) {
            this.chapters = chapters;
        }

        public List<Question> getQuestions() {
            return questions;
        }

        public void setQuestions(List<Question> questions) {
            this.questions = questions;
        }

        public class Subject {

            @SerializedName("sub_id")
            @Expose
            private String subId;
            @SerializedName("sub_name")
            @Expose
            private String subName;

            public String getSubId() {
                return subId;
            }

            public void setSubId(String subId) {
                this.subId = subId;
            }

            public String getSubName() {
                return subName;
            }

            public void setSubName(String subName) {
                this.subName = subName;
            }

        }

        public class Chapter {

            @SerializedName("chapter_id")
            @Expose
            private String chapterId;
            @SerializedName("subject_id")
            @Expose
            private String subjectId;
            @SerializedName("chapter_name")
            @Expose
            private String chapterName;

            public String getChapterId() {
                return chapterId;
            }

            public void setChapterId(String chapterId) {
                this.chapterId = chapterId;
            }

            public String getSubjectId() {
                return subjectId;
            }

            public void setSubjectId(String subjectId) {
                this.subjectId = subjectId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

        }

        public class Question {

            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("subject_id")
            @Expose
            private String subjectId;
            @SerializedName("chapter_id")
            @Expose
            private String chapterId;
            @SerializedName("question")
            @Expose
            private String question;
            @SerializedName("solution")
            @Expose
            private String solution;
            @SerializedName("options")
            @Expose
            private List<Option> options = null;
            @SerializedName("user_data")
            @Expose
            private List<UserDatum> userData = null;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSubjectId() {
                return subjectId;
            }

            public void setSubjectId(String subjectId) {
                this.subjectId = subjectId;
            }

            public String getChapterId() {
                return chapterId;
            }

            public void setChapterId(String chapterId) {
                this.chapterId = chapterId;
            }

            public String getQuestion() {
                return question;
            }

            public void setQuestion(String question) {
                this.question = question;
            }

            public String getSolution() {
                return solution;
            }

            public void setSolution(String solution) {
                this.solution = solution;
            }

            public List<Option> getOptions() {
                return options;
            }

            public void setOptions(List<Option> options) {
                this.options = options;
            }

            public List<UserDatum> getUserData() {
                return userData;
            }

            public void setUserData(List<UserDatum> userData) {
                this.userData = userData;
            }

            public class Option {

                @SerializedName("option_id")
                @Expose
                private String optionId;
                @SerializedName("code")
                @Expose
                private String code;
                @SerializedName("option")
                @Expose
                private String option;
                @SerializedName("answer")
                @Expose
                private String answer;

                public String getOptionId() {
                    return optionId;
                }

                public void setOptionId(String optionId) {
                    this.optionId = optionId;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getOption() {
                    return option;
                }

                public void setOption(String option) {
                    this.option = option;
                }

                public String getAnswer() {
                    return answer;
                }

                public void setAnswer(String answer) {
                    this.answer = answer;
                }

            }

            public class UserDatum {

                @SerializedName("user_answer")
                @Expose
                private String userAnswer;
                @SerializedName("correct_answer")
                @Expose
                private String correctAnswer;
                @SerializedName("answer_status")
                @Expose
                private String answerStatus;
                @SerializedName("subject_id")
                @Expose
                private String subjectId;
                @SerializedName("chapter_id")
                @Expose
                private String chapterId;

                public String getUserAnswer() {
                    return userAnswer;
                }

                public void setUserAnswer(String userAnswer) {
                    this.userAnswer = userAnswer;
                }

                public String getCorrectAnswer() {
                    return correctAnswer;
                }

                public void setCorrectAnswer(String correctAnswer) {
                    this.correctAnswer = correctAnswer;
                }

                public String getAnswerStatus() {
                    return answerStatus;
                }

                public void setAnswerStatus(String answerStatus) {
                    this.answerStatus = answerStatus;
                }

                public String getSubjectId() {
                    return subjectId;
                }

                public void setSubjectId(String subjectId) {
                    this.subjectId = subjectId;
                }

                public String getChapterId() {
                    return chapterId;
                }

                public void setChapterId(String chapterId) {
                    this.chapterId = chapterId;
                }

            }

        }

        public class Exam {

            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("type")
            @Expose
            private String type;
            @SerializedName("questions")
            @Expose
            private String questions;
            @SerializedName("duration")
            @Expose
            private String duration;
            @SerializedName("start_time")
            @Expose
            private String startTime;
            @SerializedName("end_time")
            @Expose
            private String endTime;
            @SerializedName("correct_answers")
            @Expose
            private String correctAnswers;
            @SerializedName("wrong_answers")
            @Expose
            private String wrongAnswers;
            @SerializedName("skipped_answers")
            @Expose
            private String skippedAnswers;
            @SerializedName("exam_status")
            @Expose
            private String examStatus;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getQuestions() {
                return questions;
            }

            public void setQuestions(String questions) {
                this.questions = questions;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getCorrectAnswers() {
                return correctAnswers;
            }

            public void setCorrectAnswers(String correctAnswers) {
                this.correctAnswers = correctAnswers;
            }

            public String getWrongAnswers() {
                return wrongAnswers;
            }

            public void setWrongAnswers(String wrongAnswers) {
                this.wrongAnswers = wrongAnswers;
            }

            public String getSkippedAnswers() {
                return skippedAnswers;
            }

            public void setSkippedAnswers(String skippedAnswers) {
                this.skippedAnswers = skippedAnswers;
            }

            public String getExamStatus() {
                return examStatus;
            }

            public void setExamStatus(String examStatus) {
                this.examStatus = examStatus;
            }

        }

    }

}
