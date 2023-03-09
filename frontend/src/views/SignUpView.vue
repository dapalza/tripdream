<template>
  <div>
    <v-form>
      <v-container>
        <v-typography>이메일 주소</v-typography>
        <v-text-field
          :loading="loading"
          label="예) dapalza@dapalza.co.kr"
          type="email"
          v-model="email"
          variant="underlined"
          density="compact"
          single-line
          required
        ></v-text-field>

        <v-typography>닉네임</v-typography>
        <v-text-field
          :loading="loading"
          label="영어, 한글 조합 00자"
          v-model="username"
          variant="underlined"
          density="compact"
          single-line
          required
        ></v-text-field>

        <v-typography>비밀번호</v-typography>
        <v-text-field
          :loading="loading"
          label="영문, 숫자, 특수문자 조합 00 ~ 00자"
          type="password"
          v-model="password"
          variant="underlined"
          density="compact"
          single-line
          required
        ></v-text-field>

        <v-typography>비밀번호 확인</v-typography>
        <v-text-field
          :loading="loading"
          label="영문, 숫자, 특수문자 조합 00 ~ 00자"
          type="password"
          v-model="passwordCheck"
          variant="underlined"
          density="compact"
          single-line
          required
        ></v-text-field>

        <v-typography>생일</v-typography>
        <v-text-field
          :loading="loading"
          label="생일 입력"
          type="number"
          v-model="height"
          variant="underlined"
          density="compact"
          single-line
          required
        ></v-text-field>

        <v-typography>키</v-typography>
        <v-text-field
          :loading="loading"
          label="선택하세요"
          type="number"
          v-model="height"
          variant="underlined"
          density="compact"
          single-line
          required
        ></v-text-field>

        <v-typography>신발 사이즈</v-typography>
        <v-text-field
          :loading="loading"
          label="선택하세요"
          type="number"
          v-model="feet"
          variant="underlined"
          density="compact"
          single-line
          required
        ></v-text-field>
        <v-btn color="primary" type="submit" @click.prevent="handleSubmit"
          >Sign Up</v-btn
        >
      </v-container>
    </v-form>
  </div>
</template>

<script>
import axios from "axios";
import { VForm, VContainer, VTextField, VBtn, VTypography } from "vuetify";

const apiClient = axios.create({
  baseURL: "http://192.168.35.107:8080",
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
  },
});

export default {
  components: {
    VForm,
    VContainer,
    VTextField,
    VBtn,
    VTypography,
  },
  data() {
    return {
      username: "",
      email: "",
      password: "",
      passwordCheck: "",
      dob: null,
      height: "",
      feet: "",
    };
  },
  methods: {
    async handleSubmit() {
      console.log("handleSubmit");

      try {
        await apiClient.post("/register", {
          username: this.username,
          email: this.email,
          password: this.password,
          dob: this.dob,
          height: this.height,
          feet: this.feet,
        });

        // 리셋
        this.username = "";
        this.email = "";
        this.password = "";
        this.passwordCheck = "";
        this.dob = null;
        this.height = "";
        this.feet = "";

        alert("@@@ 성공 @@@");
      } catch (error) {
        alert("@@@ 실패 @@@");
      }
    },
  },
};
</script>

<style></style>
