import { IUser } from './user';

export interface gameEntry {
    gameName: String,
    score: Number,
    user: IUser,
    date: Date
}
